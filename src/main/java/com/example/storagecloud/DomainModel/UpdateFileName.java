package com.example.storagecloud.DomainModel;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UpdateFileName {

    String newFilename;
    String oldFilename;



}
