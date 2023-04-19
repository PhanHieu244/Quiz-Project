package com.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Test {
    private static int sId = 0;
    private String nameTest;
    private int idTest;// id của test
    List<Question> questions =  new ArrayList<Question>();

    public Test() {
        idTest =++sId;
    }

    public Test(String nameTest) {
        idTest =++sId;
        this.nameTest = nameTest;
    }

    public Test(String nameTest, List<Question> questions) {
        this.nameTest = nameTest;
        this.questions = questions;
        idTest =++sId;

    }

    public String getNameTest() {
        return nameTest;
    }

    public int getIdTest() {
        return idTest;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int size(){
        return questions.size();
    }
    public void addQuestion(Question q){
        questions.add(q);
    }
    public List<Question> getRandomQuestions(){
        List<Question> questions_ =  new ArrayList<Question>();
        boolean check []=new boolean[this.size()];
        for(int i =0; i < this.size(); i++){
            check[i] = false;
        }
        Random rand = new Random();
        for(int i = 0 ;i < this.size(); i++){
            int ranNum = rand.nextInt(this.size());
            while(check[ranNum]){
                ranNum++;
                ranNum%=this.size();
            }
            questions_.add(questions.get(ranNum));
            check[ranNum]=true;
        }
        return questions_;
    }
}
