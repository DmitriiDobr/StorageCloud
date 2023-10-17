package com.example.storagecloud.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditNameRP {

    private String oldFilename;

    private String newFileName;


}
