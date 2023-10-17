package com.example.storagecloud.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import com.example.storagecloud.Exception.BadDataException;
import com.example.storagecloud.Exception.BadCredentialException;
import com.example.storagecloud.Exception.UploadFileException;
import com.example.storagecloud.Exception.AllFilesException;
import com.example.storagecloud.Exception.DeleteFileException;
import com.example.storagecloud.Exception.UnauthorizedException;


import com.example.storagecloud.Repository.AuthRepository;
import com.example.storagecloud.Repository.StorageRepository;
import com.example.storagecloud.Repository.UserRepository;

import com.example.storagecloud.Service.CloudStorageService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.example.storagecloud.TestData.*;
//
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StorageFileServiceTest {

    @InjectMocks
    private CloudStorageService storageFileService;
    @Mock
    private StorageRepository storageFileRepository;

    @Mock
    private AuthRepository authenticationRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(storageFileService.getUserByAuthToken(BEARER_TOKEN_1)).thenReturn(USER_1);
    }

    @Test
    void uploadFile() throws IOException {
        assertTrue(storageFileService.uploadFile(BEARER_TOKEN_1, FILENAME_1, MULTIPART_FILE.getBytes()));
    }

    @Test
    void uploadFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> storageFileService.uploadFile(BEARER_TOKEN_2, FILENAME_1, MULTIPART_FILE.getBytes()));
    }

    @Test
    void deleteFile() {
        storageFileService.deleteFileByName(BEARER_TOKEN_1, FILENAME_1);
        Mockito.verify(storageFileRepository, Mockito.times(1)).deleteByFilenameAndUsers(FILENAME_1,USER_1);
    }

    @Test
    void deleteFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> storageFileService.deleteFileByName(TOKEN_1, FILENAME_1));
    }

    @Test
    void deleteFileInputDataException() {
        Mockito.when(storageFileRepository.findByFilenameAndUsers(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(DeleteFileException.class, () -> storageFileService.deleteFileByName(BEARER_TOKEN_1, FILENAME_1));
    }

    @Test
    void downloadFile() {
        Mockito.when(storageFileRepository.findByFilenameAndUsers(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertEquals(FILE_CONTENT_1, storageFileService.downloadFile(BEARER_TOKEN_1, FILENAME_1));
    }

    @Test
    void downloadFileUnauthorized() {
        Mockito.when(storageFileRepository.findByFilenameAndUsers(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(UnauthorizedException.class, () -> storageFileService.downloadFile(TOKEN_1, FILENAME_1));
    }

    @Test
    void downloadFileInputDataException() {
        Mockito.when(storageFileRepository.findByFilenameAndUsers(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(BadDataException.class, () -> storageFileService.downloadFile(BEARER_TOKEN_1, FILENAME_2));
    }

    @Test
    void editFileNameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> storageFileService.editFilename(TOKEN_1,EDIT_FILE_NAME_RQ ));
    }

    @Test
    void editFileNameUploadDataException() {
        Mockito.when(storageFileRepository.findByFilenameAndUsers(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(UploadFileException.class, () -> storageFileService.editFilename(BEARER_TOKEN_1, EDIT_FILE_NAME_RQ_2));
    }

    @Test
    void getAllFiles() {
        Mockito.when(storageFileRepository.findAllByUsers(USER_1)).thenReturn(STORAGE_FILE_LIST);
        assertEquals(FILE_RS_LIST, storageFileService.getAllFilesByUser(BEARER_TOKEN_1));
    }

    @Test
    void getAllFilesUnauthorized() {
        Mockito.when(storageFileRepository.findAllByUsers(USER_1)).thenReturn(STORAGE_FILE_LIST);
        assertThrows(UnauthorizedException.class, () -> storageFileService.getAllFilesByUser(TOKEN_1));
    }
}
