package hocandroid.lethuy.ptit.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hocandroid.lethuy.ptit.model.Grammar;

/**
 * Created by Admin on 5/2/2017.
 */

public class GrammarDAOImpl implements GrammarDAO {
    private SQLiteDatabase db;

    public GrammarDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public List<Grammar> findAll() {
        List<Grammar> grammars = new ArrayList<>();
        Grammar grammar;
        Cursor cursor = db.rawQuery("select * from grammar", null);
        while (cursor.moveToNext()) {
            grammar = new Grammar();
            grammar.setId(cursor.getInt(0));
            grammar.setName(cursor.getString(1));
            grammar.setUse(cursor.getString(2));
            grammar.setForm(cursor.getString(3));
            grammar.setExample(cursor.getString(4));
            grammar.setNote(cursor.getString(5));
            grammars.add(grammar);
        }
        cursor.close();
        return grammars;
    }

}
