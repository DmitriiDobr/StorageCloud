package com.example.storagecloud.Dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRP {

    @JsonProperty("auth_token")
    private String authToken;
}
