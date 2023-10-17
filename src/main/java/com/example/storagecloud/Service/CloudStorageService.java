package com.example.storagecloud.Service;

import com.example.storagecloud.Exception.*;
import com.example.storagecloud.DomainModel.FileInfo;
import com.example.storagecloud.Repository.StorageRepository;
import com.example.storagecloud.DomainModel.UpdateFileName;
import com.example.storagecloud.Repository.UserRepository;
import com.example.storagecloud.Entity.StorageFiles;
import com.example.storagecloud.Entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
@Slf4j
public class CloudStorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean uploadFile(String authToken, String filename, byte[] file) {

        Users user = getUserByAuthToken(authToken);

        if (user == null) {
            log.error("Пользователь не авторизован в приложении");
            throw new UnauthorizedException("Пользователь не авторизован в приложении");

        }
        LocalDateTime time = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        storageRepository.save(new StorageFiles(filename,
                file,
                time,
                user));
        log.info("Файл успешно добавлен в базу");
        return true;

    }

    @Transactional
    public void deleteFileByName(String authToken, String filename) {
        Users user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Пользователь не авторизован в приложении");
            throw new UnauthorizedException("Пользователь не авторизован в приложении");

        }

        try {
            storageRepository.deleteByFilenameAndUsers(filename, user);
        } catch (Exception e) {
            log.error("В запрос переданы неверные данные");
            throw new BadDataException("В запрос переданы неверные данные");
        }

        StorageFiles file = storageRepository.findByFilenameAndUsers(filename, user);
        if (file != null) {
            log.error("Файл был не удален!");
            throw new DeleteFileException("Файл был не удален!");
        }
        log.info("Файл успешно удален");
    }

    public List<FileInfo> getAllFilesByUser(String authToken) {
        Users user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Пользователь не авторизован в приложении");
            throw new UnauthorizedException("Пользователь не авторизован в приложении");

        }
        try {
            List<StorageFiles> allFiles = storageRepository.findAllByUsers(user);
            List<FileInfo> files = allFiles.stream().map(item -> new FileInfo(item.getFilename(),
                    item.getContent().length)).collect(Collectors.toList());

            log.info("Все файлы для пользователя " + user.getUsername() + " загружены");
            return files;
        } catch (Exception e) {
            String message = "Невозможно получить все файлы для пользователя " + user.getUsername();
            log.error(message);
            throw new AllFilesException(message);
        }

    }

    public byte[] downloadFile(String authToken, String filename) {
        Users user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Пользователь не авторизован в приложении");
            throw new UnauthorizedException("Пользователь не авторизован в приложении");

        }
        StorageFiles storageFiles = storageRepository.findByFilenameAndUsers(filename, user);

        if (storageFiles == null) {
            log.info("Указано неправильное имя файла");
            throw new BadDataException("Указано неправильное имя файла");
        }
        return storageFiles.getContent();

    }

    @Transactional
    public boolean editFilename(String authToken, UpdateFileName name) {
        Users user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Пользователь не авторизован в приложении");
            throw new UnauthorizedException("Пользователь не авторизован в приложении");

        }


        storageRepository.editFilenameByUser(name.getNewFilename(), name.getOldFilename(), user);

        StorageFiles files = storageRepository.findByFilenameAndUsers(name.getNewFilename(), user);

        if (Objects.equals(files.getFilename(), name.getOldFilename())) {
            throw new UploadFileException("Не получилось поменять имя файла " + name.getOldFilename() + " на " + name.getNewFilename());
        }

        log.info("Для файла с именем - {} измененилось имя - {} ", name.getOldFilename(), name.getNewFilename());
        return true;
    }


    public Users getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Bearer")){
            String jwt = authToken.substring(7);
            String[] chunks = jwt.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            String[] splittedPayload = payload.replace(":", ",").replaceAll("[{}]", "").
                    split(",");
            int length = splittedPayload.length - 1;
            String newVar = splittedPayload[1].substring(1, length);
            return userRepository.findByPassword(splittedPayload[1].substring(1, length));
        }
        return null;

    }


}
