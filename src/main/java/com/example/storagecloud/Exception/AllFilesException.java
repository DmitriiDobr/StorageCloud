package com.example.storagecloud.Exception;

public class AllFilesException extends RuntimeException{
    public AllFilesException() {
        super();
    }

    public AllFilesException(String message) {
        super(message);
    }

    public AllFilesException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllFilesException(Throwable cause) {
        super(cause);
    }

    protected AllFilesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
