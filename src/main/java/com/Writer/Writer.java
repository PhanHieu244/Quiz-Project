package com.Writer;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.Question.Choice;
import com.Question.Question;
import com.Question.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
public class Writer {
    private String url;
    private String password;

    public Writer() {
    }

    public Writer(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        if(password.length()<6){
            System.out.println("Mật khẩu quá ngắn!!");
            return;
        }
        System.out.println("Chọn mật khẩu thành công!");
        this.password = password;
    }

    public void PDFWrite(Test test) {
        List<Question> questions = test.getQuestions();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(this.url));
            document.open();
            BaseFont bf = BaseFont.createFont("arial-unicode-ms.ttf", "Identity-H", true);
            Font font = new Font(bf);
            Iterator var6 = questions.iterator();

            while(var6.hasNext()) {
                Question q = (Question)var6.next();
                document.add(new Paragraph(q.getNameQuestion() + q.getContentQuestion(), font));
                if (q.getImageDataQs() != null) {
                    byte[] decoded = Base64.getDecoder().decode(q.getImageDataQs());
                    Image image = Image.getInstance(decoded);
                    document.add(image);
                }

                Iterator var13 = q.getChoices().iterator();

                while(var13.hasNext()) {
                    Choice choice = (Choice)var13.next();
                    document.add(new Paragraph(choice.getName() + ". " + choice.getContentChoice(), font));
                    if (choice.getImageDataChoice() != null) {
                        byte[] decoded = Base64.getDecoder().decode(choice.getImageDataChoice());
                        Image image = Image.getInstance(decoded);
                        document.add(image);
                    }
                }

                document.add(new Paragraph("\n"));
            }

            document.close();
        } catch (Exception var12) {
            var12.getStackTrace();
        }

    }

    public void PDFProtecteWrite(Test test) throws IOException {// tạo file có mật khẩu
        this.PDFWrite(test);
        PDDocument doc = PDDocument.load(new File(this.url));
        String password = this.password;
        StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, new AccessPermission());
        spp.setEncryptionKeyLength(128);
        spp.setPermissions(new AccessPermission());
        doc.protect(spp);
        doc.save(new File(this.url));
        doc.close();
    }
}
