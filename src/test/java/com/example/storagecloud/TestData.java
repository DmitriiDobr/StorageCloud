package com.example.storagecloud;

import com.example.storagecloud.DomainModel.FileInfo;
import com.example.storagecloud.DomainModel.UpdateFileName;
import com.example.storagecloud.Entity.StorageFiles;
import com.example.storagecloud.Entity.Users;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;
import com.example.storagecloud.Dto.request.AuthRQ;
import com.example.storagecloud.Dto.request.EditRQ;
import com.example.storagecloud.Dto.response.FileRP;
import com.example.storagecloud.Dto.response.AuthRP;
import java.time.LocalDateTime;
import java.util.List;

public class TestData {

    public static final String TOKEN_1 = "Token1";
    public static final String TOKEN_2 = "Token2";
    public static final String BEARER_TOKEN_1 = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjk2Nzk5MTYzLCJpYXQiOjE2OTY3OTg1NjN9.qqiIPSzTv4s7jNvtfjhOnXu41C0cX29AfAYgIxp1Ylc";
    public static final String BEARER_TOKEN_2 = "Token2";
    public static final String USERNAME_1 = "Username1";
    public static final String PASSWORD_1 = "Password1";
    public static final Users USER_1 = new Users(1,USERNAME_1, PASSWORD_1,true,"ADMIN",null);

    public static final String USERNAME_2 = "Username2";
    public static final String PASSWORD_2 = "Password2";
    public static final Users USER_2 = new Users(2,USERNAME_2, PASSWORD_2,true,"USER",null);
//
    public static final String FILENAME_1 = "Filename1";
    public static final Long SIZE_1 = 9L;
    public static final byte[] FILE_CONTENT_1 = FILENAME_1.getBytes();
    public static final StorageFiles STORAGE_FILE_1 = new StorageFiles(FILENAME_1, FILE_CONTENT_1, LocalDateTime.now(), USER_1);
    public static final String FILENAME_2 = "Filename2";
    public static final Long SIZE_2 = 9L;
    public static final byte[] FILE_CONTENT_2 = FILENAME_2.getBytes();
    public static final StorageFiles STORAGE_FILE_2 = new StorageFiles(FILENAME_2,  FILE_CONTENT_2,LocalDateTime.now(),USER_2);

    public static final String FOR_RENAME_FILENAME = "OldFilename";
    public static final Long FOR_RENAME_SIZE = 103L;
    public static final byte[] FOR_RENAME_FILE_CONTENT = FOR_RENAME_FILENAME.getBytes();
    public static final StorageFiles FOR_RENAME_STORAGE_FILE = new StorageFiles(FOR_RENAME_FILENAME, FOR_RENAME_FILE_CONTENT,LocalDateTime.now(),USER_1);

    public static final String NEW_FILENAME = "NewFilename";
    public static final UpdateFileName EDIT_FILE_NAME_RQ = new UpdateFileName(NEW_FILENAME, FOR_RENAME_FILENAME);

    public static final UpdateFileName EDIT_FILE_NAME_RQ_2 = new UpdateFileName(FILENAME_1, FILENAME_1);

    public static final MultipartFile MULTIPART_FILE = new MockMultipartFile(FILENAME_2, FILE_CONTENT_2);

    public static final List<StorageFiles> STORAGE_FILE_LIST = List.of(STORAGE_FILE_1, STORAGE_FILE_2);

    public static final FileInfo FILE_RS_1 = new FileInfo(FILENAME_1, (Math.toIntExact(SIZE_1)));
    public static final FileInfo FILE_RS_2 = new FileInfo(FILENAME_2, (Math.toIntExact(SIZE_2)));
    public static final List<FileInfo> FILE_RS_LIST = List.of(FILE_RS_1, FILE_RS_2);
    public static final Integer LIMIT = 100;

    public static final AuthRQ AUTHENTICATION_RQ = new AuthRQ(USERNAME_1, PASSWORD_1);
    public static final AuthRP AUTHENTICATION_RS = new AuthRP(TOKEN_1);

    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);
}