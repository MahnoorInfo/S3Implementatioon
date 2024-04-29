package com.s3implementation.AWS;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

    public String generatePreSignedUrl(String filePath, String bucketName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);

        String url = amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
        return url;
    }

    public String deleteFile(String filePath, String bucketName) {
        amazonS3.deleteObject(bucketName, filePath);
        return "File deleted";
    }
}