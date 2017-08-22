package hocandroid.lethuy.ptit.model;

import java.io.Serializable;

/**
 * Created by Admin on 5/2/2017.
 */

public class Grammar implements Serializable {
    private int  id;
    private String name;
    private String use;
    private String form;
    private String example;
    private String note;

    public Grammar(String name, int id, String use, String form, String example, String note) {
        this.name = name;
        this.id = id;
        this.use = use;
        this.form = form;
        this.example = example;
        this.note = note;
    }

    public Grammar() {

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

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return this.id+". "+this.name;
    }
}
