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
//            Process p = new ProcessBuilder("myCommand", "myArg").start();
            //byte[] bytes = new byte[0];
            //bytes = file.getBytes();
            //Path path = Paths.get(UPLOADED_FOLDER+ file.getOriginalFilename());
            //Files.write(path, bytes);
            //BufferedImage bImage = ImageIO.read(new File(path.toAbsolutePath().toString()));
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //ImageIO.write(bImage, "png", bos);
            //byte[] data = bos.toByteArray();

            BufferedImage image = null;
            byte[] imageByte;


//InputStream stream =

            byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(loginDto.getByteString());
// File of = new File("yourFile.png");
// FileOutputStream osf = new FileOutputStream(of);
// osf.write(btDataFile);
// osf.flush();

            String base64Image =loginDto.getByteString() .split(",")[1];
            byte[] imageBytes =
                    Base64.decode(base64Image);

            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(imageBytes));
// write the image to a file
            Timestamp d1 = new Timestamp(System.currentTimeMillis());
            File outputfile = new File(UPLOADED_FOLDER+"image.png");
            ImageIO.write(image1, "png", outputfile);

            //System.out.println("name " + file.getOriginalFilename());
//            Process p = Runtime.getRuntime().exec("python  /Users/biswanath/Desktop/phackers/face_rec.py");
//            ProcessBuilder processBuilder = new ProcessBuilder();
//            processBuilder.command("python Users/biswanath/Desktop/phackers/face_rec.py");
              Process process = Runtime.getRuntime().exec("python  /Users/biswanath/Desktop/phackers/face_rec.py");
              int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
//                    System.out.println(output);
                    System.exit(0);
                } else {
                    System.out.println("Success");
                    //abnormal...
                }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

//        String fetching = "python " + "c:\\Fetch.py \"" + songDetails + "\"";
//        String[] commandToExecute = new String[]{"cmd.exe", "/c", fetching};
//        Runtime.getRuntime().exec(commandToExecute);

    }


}
