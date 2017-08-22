package hocandroid.lethuy.ptit.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hocandroid.lethuy.ptit.model.Vocabulary;

/**
 * Created by Admin on 5/2/2017.
 */

public class VocabularyDAOImpl implements VocabularyDAO {
    private SQLiteDatabase db;

    public VocabularyDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public Vocabulary findOne(int id) {
        Vocabulary vocabulary = null;
        String sql = "select* from vocabulary where id = ?";
        String[] params = new String[]{String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql, params);
        cursor.moveToFirst();
        vocabulary = new Vocabulary();
        vocabulary.setWord(cursor.getString(1));
        vocabulary.setPronounce(cursor.getString(2));
        vocabulary.setMean(cursor.getString(3));
        vocabulary.setExample(cursor.getString(4));
        vocabulary.setImage(cursor.getBlob(5));
        vocabulary.setMp3(cursor.getString(6));
        vocabulary.setId_topic(cursor.getInt(7));
        cursor.close();

        return vocabulary;
    }

    @Override
    public List<Vocabulary> findTopic(int id) {
        List<Vocabulary> vocabularies = new ArrayList<>();
        Vocabulary vocabulary;
        String sql = "select* from vocabulary where id_topic = ?";
        String[] params = new String[]{String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql, params);
        while (cursor.moveToNext()) {
            vocabulary = new Vocabulary();
            vocabulary.setWord(cursor.getString(1));
            vocabulary.setPronounce(cursor.getString(2));
            vocabulary.setMean(cursor.getString(3));
            vocabulary.setExample(cursor.getString(4));
            vocabulary.setImage(cursor.getBlob(5));
            vocabulary.setMp3(cursor.getString(6));
            vocabulary.setId_topic(cursor.getInt(7));

            vocabularies.add(vocabulary);
        }
        cursor.close();
        return vocabularies;
    }


    @Override
    public int count() {
        int count = 0;
        String sql = "SELECT COUNT(id) FROM vocabulary";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        count = cursor.getInt(0);
        cursor.close();
        return count;
    }
}
