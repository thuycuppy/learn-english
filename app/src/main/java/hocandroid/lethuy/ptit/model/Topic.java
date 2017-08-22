package hocandroid.lethuy.ptit.model;

import java.io.Serializable;

/**
 * Created by Admin on 5/2/2017.
 */

public class Topic implements Serializable {
    private int id;
    private String name;
    private byte[] image;

    public Topic() {
    }

    public Topic(int id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.id+". "+this.name;
    }
}
