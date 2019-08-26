package com.example.iotaiproject.controllers;

import com.example.iotaiproject.models.dto.LoginDto;
import com.example.iotaiproject.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    LogInService logInService;

    @PostMapping(value = "/login")
    public void login(@RequestBody LoginDto loginDto){
            logInService.postLogin(loginDto);

    }
}
