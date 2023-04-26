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

    public void PDFWrite(List<Question> questions) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(this.url));
            document.open();
            BaseFont bf = BaseFont.createFont("arial-unicode-ms.ttf", "Identity-H", true);
            Font font = new Font(bf);

            for (Question q : questions) {
                document.add(new Paragraph(q.getNameQuestion() + q.getContentQuestion(), font));
                if (q.getImageDataQs() != null) {
                    byte[] decoded = Base64.getDecoder().decode(q.getImageDataQs());
                    Image image = Image.getInstance(decoded);
                    document.add(image);
                }

                for (Choice choice : q.getChoices()) {
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

    public void PDFProtectWrite(List<Question> questions ) throws IOException {// tạo file có mật khẩu
        this.PDFWrite(questions);
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
