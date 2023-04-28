package com.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Test {
    private final String nameTest;
    private final Integer idTest;// id của test
    private final Integer idParent;

    private Integer amountQuestion;
    private int generation;



    private List<Question> questions =  new ArrayList<>();

    private List<Test> children = new ArrayList<>();

    public Test() {
        nameTest = null;
        idTest = null;
        idParent = null;
    }

    public Test(Integer idTest, String nameTest, Integer idParent, List<Test> children, int generation, int totalQuestion) {
        this.nameTest = nameTest;
        this.idTest = idTest;
        this.idParent = idParent;
        this.children = children;
        this.generation = generation;
        amountQuestion = totalQuestion;
    }

    public Test(String nameTest, Integer idTest, Integer idParent, List<Question> questions) {
        this.nameTest = nameTest;
        this.idTest = idTest;
        this.idParent = idParent;
        this.questions = questions;
    }

    public Test(int id, String name, Integer idParent){
        idTest = id;
        nameTest = name;
        this.idParent = idParent;
    }

    /*public Test(List<Question> questions) {
        nameTest =null;
        idTest=null;
        idParent = null;
        this.questions = questions;
    }*/

    /*public List<Question> getRandomQuestions(){
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
    }*/

    public int getAmountQuestion(){ return amountQuestion;}

    public List<Test> getChildren() {
        return children;
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

    public int getGeneration() {
        return generation;
    }

    public Integer getIdParent() {
        return idParent;
    }
}
