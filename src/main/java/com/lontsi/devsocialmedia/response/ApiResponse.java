package com.lontsi.devsocialmedia.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean status;
}
