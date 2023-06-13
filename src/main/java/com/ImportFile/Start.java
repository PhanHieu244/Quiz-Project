package com.ImportFile;
import com.Question.AikenException;
import com.Question.Question;
import com.Question.ReaderQsWord;
import com.Question.ReaderQuestion;
import com.Writer.Writer;
import java.io.IOException;
import java.util.List;

public class Start {
    public Start() {
    }

    public static void main(String[] args) throws IOException {
        ReaderQuestion rd = new ReaderQsWord("C:\\Users\\ADMIN\\IdeaProjects\\projectOOP\\inputData\\test.docx");
        Writer writer = new Writer("C:\\Users\\ADMIN\\IdeaProjects\\projectOOP\\outputData\\output1.pdf");
        writer.PDFWrite(rd.read().getQuestions());
//        writer.PDFProtectWrite(rd.read().getQuestions(),"sondeptraiqua");
//        rd.read().getQuestions().get(2).show();
    }
}
