package com.example.storagecloud.Dto.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class EditRQ {

    String filename;

    String newFilename;

    @JsonCreator
    public EditRQ(String filename,String newFilename) {
        this.filename = filename;
        this.newFilename = newFilename;
    }
}
