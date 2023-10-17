package com.example.storagecloud.Exception;

public class DeleteFileException extends RuntimeException {
    public DeleteFileException() {
        super();
    }

    public DeleteFileException(String message) {
        super(message);
    }

    public DeleteFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteFileException(Throwable cause) {
        super(cause);
    }

    protected DeleteFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
