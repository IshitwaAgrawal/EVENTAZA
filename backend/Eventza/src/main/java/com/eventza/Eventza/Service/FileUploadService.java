package com.eventza.Eventza.Service;

import com.eventza.Eventza.Events.EventModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileUploadService {

    public void fileUpload(MultipartFile file, EventModel eventModel)throws Exception{
        file.transferTo(new File("C:\\Users\\ISHITWA\\Desktop\\EVENTAZA\\backend\\Eventza\\src\\main\\resources\\Files\\"+eventModel.getBrochure_name()));
    }

    public void imageUpload(MultipartFile image, EventModel event)throws Exception{
        image.transferTo(new File("D:\\eventazaData\\eventImages\\" + event.getId().toString()));
    }

    public void documentUpload(MultipartFile file, String username)throws Exception{
        file.transferTo(new File("D:\\eventazaData\\eventIdProof\\" + username));
    }
}
