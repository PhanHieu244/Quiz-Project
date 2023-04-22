package com.ImportFile;
import com.Question.ReaderQsWord;
import com.Writer.Writer;
import java.io.IOException;

public class Start {
    public Start() {
    }

    public static void main(String[] args) throws IOException {
        ReaderQsWord rd = new ReaderQsWord("C:\\Users\\ADMIN\\IdeaProjects\\projectOOP\\inputData\\demo-apache-apoi-word.docx");
        Writer writer = new Writer("C:\\Users\\ADMIN\\IdeaProjects\\projectOOP\\outputData\\output1.pdf");
        writer.setPassword("123456");// nhập mật khẩu vào đây
        writer.PDFProtecteWrite(rd.read());
//        rd.read().getQuestions().get(2).show();
    }
}
