package com.example.iotaiproject.utils;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CustomResponse {
    private String message;
    private Object data;

}
