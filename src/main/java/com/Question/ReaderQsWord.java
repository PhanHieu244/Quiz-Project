package com.Question;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ReaderQsWord extends ReaderQuestion {
    private int idImage = 0;

    public ReaderQsWord(String url){
        this.url = url;
    }

    public Test read(){
        Test test = new Test();

        try {
            FileInputStream fis = new FileInputStream(this.url);
            XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
            List<String> list = new ArrayList<>();
            for (XWPFPictureData pictureData : document.getAllPictures()) {
                byte[] imageData = pictureData.getData();
                String encoded = Base64.getEncoder().encodeToString(imageData);
                if (encoded != null) {
                    list.add(encoded);
                }
            }

            int count = 0;
            boolean flag = true;
            int error = 0;
            List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
            int numOfImage = list.size();
            boolean isImageMixed = numOfImage > 5;

            int numContentQs = 0;
            int numChoices = 0;
            int numKey = 0;
            Question question = new Question();
            int i = 1;
            System.out.println("đoạn này ở class ReaderQdWord");
            Iterator var14 = xwpfParagraphs.iterator();

            label:
            while(true) {
                while(true) {
                    if (!var14.hasNext()) {
                        break label;
                    }

                    XWPFParagraph x = (XWPFParagraph)var14.next();
                    ++error;
                    boolean hasPic = false;
                    List<XWPFRun> runs = x.getRuns();

                    for (XWPFRun run : runs) {
                        if (run.getEmbeddedPictures().size() > 0) {
                            System.out.println("có ảnh ở paragraph này");
                            hasPic = true;
                        }
                    }

                    String line = x.getText();
                    int var10001 = i++;
                    System.out.println(var10001 + " " + line);
                    if (numContentQs != 0) {
                        if (line.length() == 0) {
                            if (numChoices < 2 || numKey != 1) {
                                flag = false;
                                break label;
                            }

                            if (!question.setKeyChoice()) {
                                System.out.println("Không tìm ra key");
                                flag = false;
                                break label;
                            }

                            test.addQuestion(question);
                            numContentQs = 0;
                            numChoices = 0;
                            numKey = 0;
                            question = new Question();
                        } else {
                            if (line.length() <= 3) {
                                flag = false;
                                break label;
                            }

                            if (line.charAt(2) == ' ' && line.charAt(1) == '.' && numKey < 1 && line.charAt(0) <= 'Z' && line.charAt(0) >= 'A') {
                                ++numChoices;
                                question.addChoice(line.substring(3), line.charAt(0));
                                if (hasPic) {
                                    int id = isImageMixed? ((idImage-5+numOfImage)%numOfImage) : idImage;
                                    question.setImageDataLastChoice((String)list.get(id));
                                    idImage++;
                                }
                            } else {
                                if (line.length() <= 8 || 0 != line.indexOf("ANSWER: ")) {
                                    flag = false;
                                    break label;
                                }

                                ++numKey;
                                question.setKey(line.substring(8));
                            }
                        }
                    } else {
                        if (line.length() < 1) {
                            flag = false;
                            break label;
                        }

                        question.setNameAndContentQs(line);
                        if (hasPic) {
                            int id = isImageMixed? (idImage-5+numOfImage)%numOfImage:idImage;
                            question.setImageDataQs((String)list.get(id));
                            idImage++;
                        }

                        ++numContentQs;
                        ++count;
                    }
                }
            }

            if (numContentQs != 0) {
                flag = false;
            }

            if (!flag) {
                test = null;
                throw new AikenException(error);
            }

            System.out.println("=============================");
            System.out.println("Success " + count);
            document.close();
            fis.close();
        } catch (Exception var20) {
            var20.printStackTrace();
        }

        return test;
    }


}
