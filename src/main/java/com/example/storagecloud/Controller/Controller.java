package com.example.storagecloud.Controller;


import com.example.storagecloud.Dto.request.EditRQ;
import com.example.storagecloud.Dto.response.FileRP;
import com.example.storagecloud.DomainModel.FileInfo;
import com.example.storagecloud.DomainModel.UpdateFileName;
import com.example.storagecloud.Service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    private CloudStorageService cloudStorageService;


    @PostMapping("/file")
    public ResponseEntity<?>getFile(@RequestHeader("auth-token") String AuthToken,
                                    @RequestParam("filename") String filename,
                                    MultipartFile file) throws IOException {
        byte[] byteFile = file.getBytes();
        cloudStorageService.uploadFile(AuthToken,filename,byteFile);
        return ResponseEntity.ok(HttpStatus.OK);


    }


    @DeleteMapping("/file")
    public ResponseEntity<?>deleteFile(@RequestHeader("auth-token") String AuthToken,
                                       @RequestParam("filename") String filename){
        cloudStorageService.deleteFileByName(AuthToken,filename);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/file")
    public ResponseEntity<?> changeFile(@RequestHeader("auth-token") String AuthToken,
                                        EditRQ editRQ){
        UpdateFileName name= new UpdateFileName(editRQ.getNewFilename(), editRQ.getFilename());
        cloudStorageService.editFilename(AuthToken, name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    @ResponseBody
    public ByteArrayResource getFile(@RequestHeader("auth-token") String authToken,String filename){
        byte[] file = cloudStorageService.downloadFile(authToken,filename);
        return new ByteArrayResource(file);
    }


    @GetMapping("/list")
    @ResponseBody
    public List<FileRP> getAllFiles(@RequestHeader("auth-token") String authToken){
        List<FileInfo> files= cloudStorageService.getAllFilesByUser(authToken);
        return files.stream().map(item -> new FileRP(item.getFilename(),item.getSize())).
                collect(Collectors.toList());
    }




}
