package com.ImportFile;
import com.Question.AikenException;
import com.Question.ReaderQsWord;
import com.Question.ReaderQuestion;
import com.Writer.Writer;
import java.io.IOException;

public class Start {
    public Start() {
    }

    public static void main(String[] args) throws IOException {
        ReaderQuestion rd = new ReaderQsWord("C:\\Users\\Administrator\\IdeaProjects\\QuizProject\\inputData\\demo-apache-apoi-word.docx");
        Writer writer = new Writer("C:\\Users\\Administrator\\IdeaProjects\\QuizProject\\outputData\\output1.pdf");
        /*writer.setPassword("123456");// nhập mật khẩu vào đây
        writer.PDFProtecteWrite(rd.read());*/
        rd.read().getQuestions().get(2).show();
    }
}
