package com.example.iotaiproject.services;


import com.example.iotaiproject.models.dto.RegisterDto;
import com.example.iotaiproject.models.entity.ImageFilesEntity;
import com.example.iotaiproject.models.entity.ImagePathTable;
import com.example.iotaiproject.models.entity.RegisterEntity;
import com.example.iotaiproject.models.entity.knownPeopleTable;
import com.example.iotaiproject.repo.IamgePathRepo;
import com.example.iotaiproject.repo.ImageUploadRepo;
import com.example.iotaiproject.repo.RegisterRepo;
import com.example.iotaiproject.utils.CustomResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RegisterRepo registerRepo;

    @Autowired
    ImageUploadRepo imageUploadRepo;

    @Autowired
    IamgePathRepo iamgePathRepo;



    CustomResponse customResponse = new CustomResponse();
    //String UPLOADED_FOLDER = "C:\\Users\\Chidambar\\IdeaProjects\\iotaiproject\\src\\main\\java\\com\\example\\iotaiproject\\knownPeople\\";
    String UPLOADED_FOLDER = "/Users/biswanath/Desktop/phackers/emp_img/";

    public CustomResponse upload(RegisterDto registerDto) {

        List<ImagePathTable> imageFilesEntitiesList = new ArrayList<>();
        knownPeopleTable knownPeopleTable = new knownPeopleTable();
        RegisterEntity registerEntity = modelMapper.map(registerDto,RegisterEntity.class);

        List<ImageFilesEntity> imageFilesEntities = registerEntity.getImagesai();

        for(ImageFilesEntity image: imageFilesEntities) {
          Optional<ImageFilesEntity> imageFilesEntity =imageUploadRepo.findById(image.getImageId());
              if(imageFilesEntity.isPresent() && imageFilesEntity.get().getEmpId().equals(registerEntity.getEmpId())){
                  knownPeopleTable.setEmpId(registerEntity.getEmpId());
                  if(imageFilesEntity.get().getImageId().equals(image.getImageId())) {
                      ImagePathTable imageFilesEntity1 = new ImagePathTable();
                      imageFilesEntity1.setBinaryImageFile(imageFilesEntity.get().getBinaryImageFile());
                      imageFilesEntity1.setImagePath(imageFilesEntity.get().getSourPath());
                      imageFilesEntitiesList.add(imageFilesEntity1);
                      knownPeopleTable.setImagesai(imageFilesEntitiesList);
                  }
                  else{
                      customResponse.setData("Invalid Image Id");
                      break;
                  }
             }
              else
              {
                  customResponse.setData("Invalid Emp ID");
                  break;
              }
          }
           iamgePathRepo.save(knownPeopleTable);

            registerEntity.setImagesai(imageFilesEntities);
            RegisterEntity registerEntityFinal = registerRepo.save(registerEntity);
            RegisterDto registerDtoFinal = modelMapper.map(registerEntityFinal, RegisterDto.class);
            customResponse.setData(registerDtoFinal);
             return customResponse;
    }


    public CustomResponse uploadDetails(MultipartFile[] files,String empId,String name) {

        try {
            Optional<RegisterEntity> optionalRegisterEntity = registerRepo.findByEmpId(empId);
            if(!optionalRegisterEntity.isPresent()) {

                RegisterEntity registerEntity = new RegisterEntity();
                List<ImageFilesEntity> imageFilesEntitiesList = new ArrayList<>();

                registerEntity.setName(name);
                registerEntity.setEmpId(empId);


//                File newFolder = new File(UPLOADED_FOLDER + "image_" + empId);
//                String newPath = null;
//                if (!newFolder.exists()) {
//                    boolean created = newFolder.mkdir();
//                }


                for (MultipartFile file : files) {
                    ImageFilesEntity imageFilesEntity = new ImageFilesEntity();
//                if (file.getContentType() != null && file.getContentType().equals("image/png")) {
                    byte[] bytes = new byte[0];
                    try {
                        bytes = file.getBytes();
                        Path path = Paths.get(UPLOADED_FOLDER + "empId_" + empId + "_" + file.getOriginalFilename());
                        Files.write(path, bytes);
                        BufferedImage bImage = ImageIO.read(new File(path.toAbsolutePath().toString()));
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(bImage, "png", bos);
                        byte[] data = bos.toByteArray();
                        imageFilesEntity.setByteStringImageFile(data.toString());
                        imageFilesEntity.setSourPath(path.toAbsolutePath().toString());
                        BufferedImage image = ImageIO.read(new File(path.toAbsolutePath().toString()));
                        ByteArrayOutputStream b = new ByteArrayOutputStream();
                        ImageIO.write(image, "png", b);

                        // do whatever with the array...
                        byte[] jpgByteArray = b.toByteArray();

                        // convert it to a String with 0s and 1s
                        StringBuilder sb = new StringBuilder();
                        for (byte by : jpgByteArray)
                            sb.append(Integer.toBinaryString(by & 0xFF));

                        //imageFilesEntity.setBinaryImageFile(sb.toString());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                } else {
//                    customResponse.setData("Image format should be .png");
//                    break;
//
//                }
                    imageFilesEntitiesList.add(imageFilesEntity);
                }
                registerEntity.setImagesai(imageFilesEntitiesList);
                RegisterEntity entity = registerRepo.save(registerEntity);
                customResponse.setMessage("Registered Successfully Thank you");
                customResponse.setData(entity);
            }
            else
                customResponse.setMessage("Employee is already registered,Please try to login");
        }
        catch (Exception e){
            customResponse.setMessage("Could not create an Entry");
        }

        return customResponse;
    }


    public CustomResponse deleteDetails(Long detailId) {
        try{
           Optional<RegisterEntity> registerEntity = registerRepo.findById(detailId);
           String dir =UPLOADED_FOLDER + "image_" + registerEntity.get().getEmpId();
            File newFolder = new File(dir);
            if(newFolder.exists()) {
                newFolder.delete();
            }
            registerRepo.delete(registerEntity.get());
            customResponse.setMessage("Entry Deleted");

        }catch (Exception e){
            customResponse.setMessage("Could not delete the Entry");
        }
        return  customResponse;
    }

    public CustomResponse viewDetails(){
        try{
            List<RegisterEntity> registerEntities = registerRepo.findAll();
            customResponse.setData(registerEntities);
            customResponse.setMessage("Details");


        }catch (Exception e){
            customResponse.setMessage("Could not get details - Internal server");
        }

        return customResponse;
    }
}
