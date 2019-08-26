package com.example.iotaiproject.controllers;

import com.example.iotaiproject.models.dto.RegisterDto;
import com.example.iotaiproject.services.RegisterService;
import com.example.iotaiproject.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RegisterApiController {


    @Autowired
    RegisterService registerService;



    @PostMapping(value="/uploadImage")
    public ResponseEntity<CustomResponse> uploadImage(@RequestParam MultipartFile[] file, @RequestParam String empId, @RequestParam String name){

        CustomResponse customResponse= null;
        try {
            customResponse = registerService.uploadDetails(file,empId,name);
        } catch (Exception e) {
            e.printStackTrace();
            customResponse.setData("");
            customResponse.setMessage("Oops something went wrong please upload the file again");
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customResponse,HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteDetails")
    public ResponseEntity<CustomResponse> deleteDetails(@RequestParam Long detailId ){

        CustomResponse customResponse= null;
        try {
            customResponse = registerService.deleteDetails(detailId);
        } catch (Exception e) {
            e.printStackTrace();
            customResponse.setData("");
            customResponse.setMessage("Oops something went wrong please upload the file again");
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customResponse,HttpStatus.OK);

    }


    @GetMapping(value = "/getDetails")
    public ResponseEntity<CustomResponse> getDetails(){

        CustomResponse customResponse= null;
        try {
            customResponse = registerService.viewDetails();
        } catch (Exception e) {
            e.printStackTrace();
            customResponse.setData("");
            customResponse.setMessage("Oops something went wrong please upload the file again");
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customResponse,HttpStatus.OK);

    }
}
