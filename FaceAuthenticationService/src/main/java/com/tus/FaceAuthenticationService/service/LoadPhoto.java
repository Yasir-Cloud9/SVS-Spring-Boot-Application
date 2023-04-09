package com.tus.FaceAuthenticationService.service;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class LoadPhoto
{
    public byte[] loadPhoto(String filePath) {
        File file = new File(filePath);
        try
        {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fis.read(bytes);
            fis.close();
            return bytes;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
