package hocandroid.lethuy.ptit.model;

import java.io.Serializable;

/**
 * Created by Admin on 5/2/2017.
 */

public class Vocabulary implements Serializable {
    private int id;
    private String word;
    private String pronounce;
    private String mean;
    private String example;
    private byte[] image;
    private String mp3;
    private int id_topic;

    public Vocabulary(int id, String word, String pronounce, String mean, String example, byte[] image, String mp3, int id_topic) {
        this.id = id;
        this.word = word;
        this.pronounce = pronounce;
        this.mean = mean;
        this.example = example;
        this.image = image;
        this.mp3 = mp3;
        this.id_topic = id_topic;
    }

    public Vocabulary(String word, String pronounce, String mean, String example, byte[] image, String mp3, int id_topic) {
        this.word = word;
        this.pronounce = pronounce;
        this.mean = mean;
        this.example = example;
        this.image = image;
        this.mp3 = mp3;
        this.id_topic = id_topic;
    }

    public Vocabulary() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }
}