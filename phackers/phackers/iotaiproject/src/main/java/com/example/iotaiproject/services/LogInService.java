package com.example.iotaiproject.services;

import com.example.iotaiproject.models.dto.LoginDto;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.sql.Timestamp;

import org.apache.commons.io.IOUtils;
import org.omg.SendingContext.RunTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class LogInService {

    //String UPLOADED_FOLDER = "C:\\Users\\Chidambar\\IdeaProjects\\iotaiproject\\src\\main\\java\\com\\example\\iotaiproject\\unknownPeople\\";
    String UPLOADED_FOLDER = "/Users/biswanath/Desktop/phackers/unknown_face/";

    public void postLogin(LoginDto loginDto){

        try {
            BufferedImage image = null;

            String base64Image =loginDto.getByteString() .split(",")[1];
            byte[] imageBytes =
                    Base64.decode(base64Image);

            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(imageBytes));
            // write the image to a file
            Timestamp d1 = new Timestamp(System.currentTimeMillis());
            File outputfile = new File(UPLOADED_FOLDER+"image.png");
            ImageIO.write(image1, "png", outputfile);

            System.out.println("name " + "image.png");
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("python Users/biswanath/Desktop/phackers/face_rec.py");
              Process process = Runtime.getRuntime().exec("python /Users/biswanath/Desktop/phackers/face_rec.py image.png");
              int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                } else {
                    System.out.println("UnSuccess");
                    //abnormal...
                }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }



    }


}
