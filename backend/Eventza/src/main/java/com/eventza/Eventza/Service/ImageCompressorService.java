package com.eventza.Eventza.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import org.springframework.stereotype.Service;

@Service
public class ImageCompressorService {


  public byte[] compressImageBytes(byte[] imageData) throws IOException {

    Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
    deflater.setInput(imageData);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageData.length);
    deflater.finish();
    byte[] buffer = new byte[1024];
    while (!deflater.finished()) {
      int count = deflater.deflate(buffer);
      outputStream.write(buffer, 0, count);
    }
    outputStream.close();
    byte[] outputData = outputStream.toByteArray();

    System.out.println("Original: " + imageData.length / 1024 + " Kb");
    System.out.println("Compressed: " + outputData.length / 1024 + " Kb");
    return outputData;
  }
}
