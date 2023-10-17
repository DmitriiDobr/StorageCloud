package com.example.storagecloud.DomainModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfo {
    private String filename;

    private Integer size;
}
