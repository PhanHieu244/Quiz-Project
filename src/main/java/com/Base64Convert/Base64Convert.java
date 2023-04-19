package com.Base64Convert;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.*;
import java.util.Base64;

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
}
