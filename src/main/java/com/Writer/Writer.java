package com.Writer;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.Question.Choice;
import com.Question.Question;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
public class Writer {
    private String url;
    //private String password;

    public Writer() {
    }

    public Writer(String url) {
        this.url = url;
    }

//    public void setPassword(String password) {
//        if(password.length()<6){
//            System.out.println("Mật khẩu quá ngắn!!");
//            return;
//        }
//        System.out.println("Chọn mật khẩu thành công!");
//        this.password = password;
//    }

    public void PDFWrite(List<Question> questions) {

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.url));
            document.open();
            BaseFont bf = BaseFont.createFont("arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            int fontSize = 10;
            Font font = new Font(bf,fontSize);
            float spaceLeft=1000;
            float spacingBefore=0;
            String string = null;
            Paragraph para=null;
            Image image = null;
            boolean hasImage = false;
            for (Question q : questions) {
                string = q.getNameQuestion()==null?q.getContentQuestion():q.getNameQuestion() + ": " + q.getContentQuestion();
                para= new Paragraph(string,font);
                hasImage = q.getImageDataQs() != null;
                if(hasImage){
                    byte[] decoded = Base64.getDecoder().decode(q.getImageDataQs());
                    image=Image.getInstance(decoded);
                }

                spacingBefore= spaceLeft>=image.getHeight()+fontSize*(int)(string.length()/100+1)?0:spaceLeft;
                document.add(para);
                para.setSpacingBefore(spacingBefore);
                if (hasImage){
                    document.add(image);
                    hasImage=false;
                }
                spaceLeft = writer.getVerticalPosition(true)-document.bottomMargin();

                int idAns = 0;
                for (Choice choice : q.getChoices()) {
                    string = (char)((int)'A'+idAns++) + ". " + choice.getContentChoice();
                    para= new Paragraph(string,font);
                    hasImage = choice.getImageDataChoice() != null;
                    if(hasImage){
                    byte[] decoded = Base64.getDecoder().decode(choice.getImageDataChoice());
                    image=Image.getInstance(decoded);
                    }
                    spacingBefore= spaceLeft>=image.getHeight()+fontSize*(int)(string.length()/100+1)?0:spaceLeft;
                    para.setSpacingBefore(spacingBefore);
                    document.add(para);
                    if (hasImage){
                        document.add(image);
                        hasImage=false;
                    }
                    spaceLeft = writer.getVerticalPosition(true)-document.bottomMargin();
                }

                document.add(new Paragraph("ANSWER: "+q.getKey(),font));
            }

            document.close();
        } catch (Exception var12) {
            var12.getStackTrace();
        }

    }
    public void PDFProtectWrite(List<Question> questions, String password ) throws IOException {// tạo file có mật khẩu
        if (password.length()<6||password.indexOf(" ")>=0) {
            System.out.println("mật khẩu lỗi");
            return;
        }
        this.PDFWrite(questions);
        PDDocument doc = PDDocument.load(new File(this.url));
        StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, new AccessPermission());
        spp.setEncryptionKeyLength(128);
        spp.setPermissions(new AccessPermission());
        doc.protect(spp);
        doc.save(new File(this.url));
        doc.close();
    }
}
