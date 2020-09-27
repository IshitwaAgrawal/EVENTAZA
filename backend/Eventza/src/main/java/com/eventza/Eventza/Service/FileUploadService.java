package com.eventza.Eventza.Service;

import com.eventza.Eventza.Events.EventModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileUploadService {

    public void fileUpload(MultipartFile file, EventModel eventModel)throws Exception{
        file.transferTo(new File("C:\\Users\\ISHITWA\\Desktop\\EVENTAZA\\backend\\Eventza\\src\\main\\resources\\Files\\"+eventModel.getEventName()));
    }
}
