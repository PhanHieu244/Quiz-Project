package com.Base64Convert;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Base64Convert {
    private static Base64Convert instance;

    public Base64Convert( ){ }

    public Base64Convert getInstance(){
        if (instance == null){
            instance = this;
        }
        return instance;
    }

    public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.getEncoder().encode(bytes));
            //encodedfile = Base64.getEncoder().encode(bytes).toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
        }

        return encodedfile;
    }

    public static Image base64ToImage(String base64String){
        if (base64String == null) return null;
        if (base64String.equals("")) return null;
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        return new Image(new ByteArrayInputStream(imageBytes));
    }

    public static Media base64ToMedia(String base64String){
        if (base64String == null) return null;
        if (base64String.equals("")) return null;
        byte[] videoBytes = Base64.getDecoder().decode(base64String);

        try {
            // Create a new ByteArrayInputStream from the byte array
            ByteArrayInputStream inputStream = new ByteArrayInputStream(videoBytes);

            // Save the byte array to a temporary video file
            FileOutputStream outputStream = new FileOutputStream("temp.mp4");
            outputStream.write(videoBytes);
            outputStream.close();

            // Create a new Media object from the temporary video file
            return new Media(new File("temp.mp4").toURI().toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isVideoFile(File file){
        String nameFile = file.getName();
        return nameFile.endsWith(".mp4") || nameFile.endsWith(".mkv")
                || nameFile.endsWith(".avi");
    }

    public static boolean isImageFile(File file){
        String nameFile = file.getName();
        return nameFile.endsWith(".png") || nameFile.endsWith(".jpg")
                || nameFile.endsWith(".gif");
    }

    public static boolean isImage(String base64String) {
        // Decode the Base64 string to byte array
        byte[] data = Base64.getDecoder().decode(base64String);

        // Check the file signature for image formats
        if (data.length >= 2) {
            return (data[0] == (byte) 0xFF && data[1] == (byte) 0xD8) // JPEG
                    || (data[0] == (byte) 0x89 && data[1] == (byte) 0x50) // PNG
                    || (data[0] == (byte) 0x47 && data[1] == (byte) 0x49) // GIF
                    || (data[0] == (byte) 0x42 && data[1] == (byte) 0x4D); // BMP
        }

        return false;
    }

    public static boolean isVideo(String base64String) {
        // Decode the Base64 string to byte array
        byte[] data = Base64.getDecoder().decode(base64String);

        // Check the file signature for video formats
        if (data.length >= 4) {
            return (data[0] == (byte) 0x00 && data[1] == (byte) 0x00 && data[2] == (byte) 0x00 && data[3] == (byte) 0x18) // MP4
                    || (data[0] == (byte) 0x1A && data[1] == (byte) 0x45 && data[2] == (byte) 0xDF && data[3] == (byte) 0xA3); // WebM
        }

        return false;
    }

}
