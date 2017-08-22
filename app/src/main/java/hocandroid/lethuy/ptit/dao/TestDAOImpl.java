package hocandroid.lethuy.ptit.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hocandroid.lethuy.ptit.model.Test;

/**
 * Created by Admin on 5/10/2017.
 */

public class TestDAOImpl implements TestDAO {
    private SQLiteDatabase db;

    public TestDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public List<Test> findAll(int id_grammar) {
        List<Test> tests = new ArrayList<>();
        Test test;
                String sql = "select* from test where id_grammar = ?";
        String[] params = new String[]{String.valueOf(id_grammar)};
        Cursor cursor = db.rawQuery(sql, params);
        while (cursor.moveToNext()) {
            test = new Test();
            test.setId(cursor.getInt(0));
            test.setQuestion(cursor.getString(1));
            test.setChooseA(cursor.getString(2));
            test.setChooseB(cursor.getString(3));
            test.setChooseC(cursor.getString(4));
            test.setChooseD(cursor.getString(5));
            test.setCorrect(cursor.getString(6));
            test.setId_grammar(cursor.getInt(7));

            tests.add(test);
        }
        cursor.close();
        return tests;
    }
}
