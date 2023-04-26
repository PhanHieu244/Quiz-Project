package com.Question;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class ReaderQsTxt {
    private String url;

    public ReaderQsTxt() {
    }

    public ReaderQsTxt(String url) {
        this.url = url;
    }

    public Test read() throws AikenException {
        Test test = new Test();

        try {
            File f = new File(this.url);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int count = 0;
            boolean flag = true;
            int error = 0;

            String line;
            while((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    ++error;
                    flag = false;
                    break;
                }

                ++count;
                Question question = new Question(line);
                ++error;
                int numChoices = 0;
                int numKey = 0;

                while((line = br.readLine()) != null) {
                    if (line.length() == 0) {
                        ++error;
                        break;
                    }

                    if (line.length() <= 3) {
                        flag = false;
                        break;
                    }

                    if (line.charAt(2) == ' ' && line.charAt(1) == '.' && numKey < 1 && line.charAt(0) <= 'Z' && line.charAt(0) >= 'A') {
                        ++numChoices;
                        ++error;
                        question.addChoice(line.substring(3), line.charAt(0));
                    } else {
                        if (line.length() <= 8 || 0 != line.indexOf("ANSWER: ")) {
                            flag = false;
                            break;
                        }

                        ++numKey;
                        ++error;
                        question.setKey(line.substring(8));
                    }
                }

                if (numChoices < 2 || numKey != 1) {
                    flag = false;
                    break;
                }

                if (!flag) {
                    throw new AikenException(error);
                }

                if (!question.setKeyChoice()) {
                    System.out.println("Không tìm ra key");
                    flag = false;
                    break;
                }

                test.addQuestion(question);
            }

            if (!flag) {
                throw new AikenException(error);
            }

            System.out.println("Success " + count);
            fr.close();
            br.close();
        } catch (Exception var12) {
            var12.getStackTrace();
        }

        return test;
    }
}
