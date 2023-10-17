package com.example.storagecloud.Dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
public class AuthRQ {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
