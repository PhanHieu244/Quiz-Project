package com.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Test {
    private final String nameTest;
    private final Integer idTest;// id của test
    private final Integer idParent;

    private int generation;



    private List<Question> questions =  new ArrayList<>();

    private List<Test> children = new ArrayList<>();

    public Test(Integer idTest, String nameTest, Integer idParent, List<Test> children, int generation) {
        this.nameTest = nameTest;
        this.idTest = idTest;
        this.idParent = idParent;
        this.children = children;
        this.generation = generation;
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

    public int getAmountQuestion(){ return questions.size();}

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
}
