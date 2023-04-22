package com.Question;

public class AikenException extends Exception{
    public AikenException() {
    }

    public AikenException(int error) {
        System.out.println("error at " + error);
    }
}
