package com.example.storagecloud.ExceptionController;


import com.example.storagecloud.Dto.response.ApiExceptionRP;
import com.example.storagecloud.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionApiHandler extends RuntimeException {

    @ExceptionHandler(value ={BadCredentialException.class} )
    public ResponseEntity<Object> handleBadCredentials(BadCredentialException e){
        ApiExceptionRP apiExceptionRP = new ApiExceptionRP(
            e.getMessage(),
            e,
            HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return new ResponseEntity<>(apiExceptionRP,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value ={BadDataException.class})
    public ResponseEntity<Object>handleBadData(BadDataException e){
        ApiExceptionRP apiExceptionRP = new ApiExceptionRP(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        );
        return new ResponseEntity<>(apiExceptionRP,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Object>handleUnauthorize(UnauthorizedException e){
        ApiExceptionRP apiExceptionRP = new ApiExceptionRP(e.getMessage(),e,HttpStatus.UNAUTHORIZED,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return new ResponseEntity<>(apiExceptionRP,HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(value = {AllFilesException.class})
    public ResponseEntity<Object>handleAllFiles(AllFilesException e){
        ApiExceptionRP apiExceptionRP = new ApiExceptionRP(e.getMessage(),e,HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return new ResponseEntity<>(apiExceptionRP,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UploadFileException.class})
    public ResponseEntity<Object>handleAllFiles(UploadFileException e){
        ApiExceptionRP apiExceptionRP = new ApiExceptionRP(e.getMessage(),e,HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return new ResponseEntity<>(apiExceptionRP,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {DeleteFileException.class})
    public ResponseEntity<Object>handleAllFiles(DeleteFileException e){
        ApiExceptionRP apiExceptionRP = new ApiExceptionRP(e.getMessage(),e,HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return new ResponseEntity<>(apiExceptionRP,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
