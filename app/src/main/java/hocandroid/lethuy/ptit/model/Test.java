package hocandroid.lethuy.ptit.model;

import java.io.Serializable;

/**
 * Created by Admin on 5/10/2017.
 */

public class Test implements Serializable {
    private int id;
    private String question;
    private String chooseA;
    private String chooseB;
    private String chooseC;
    private String chooseD;
    private String correct;
    private int id_grammar;

    public Test() {
    }

    public Test(int id, String question, String chooseA, String chooseB, String chooseC, String chooseD, String correct, int id_grammar) {
        this.id = id;
        this.question = question;
        this.chooseA = chooseA;
        this.chooseB = chooseB;
        this.chooseC = chooseC;
        this.chooseD = chooseD;
        this.correct = correct;
        this.id_grammar = id_grammar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChooseA() {
        return chooseA;
    }

    public void setChooseA(String chooseA) {
        this.chooseA = chooseA;
    }

    public String getChooseB() {
        return chooseB;
    }

    public void setChooseB(String chooseB) {
        this.chooseB = chooseB;
    }

    public String getChooseC() {
        return chooseC;
    }

    public void setChooseC(String chooseC) {
        this.chooseC = chooseC;
    }

    public String getChooseD() {
        return chooseD;
    }

    public void setChooseD(String chooseD) {
        this.chooseD = chooseD;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public int getId_grammar() {
        return id_grammar;
    }

    public void setId_grammar(int id_grammar) {
        this.id_grammar = id_grammar;
    }
}
