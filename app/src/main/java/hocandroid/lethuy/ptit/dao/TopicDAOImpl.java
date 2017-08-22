package hocandroid.lethuy.ptit.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hocandroid.lethuy.ptit.model.Topic;

/**
 * Created by Admin on 5/2/2017.
 */

public class TopicDAOImpl implements TopicDAO {
    private SQLiteDatabase db;

    public TopicDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public List<Topic> findAll() {
        List<Topic> topics = new ArrayList<>();
        Topic topic;
        Cursor cursor = db.rawQuery("select* from topic",null);
        while (cursor.moveToNext()){
            topic = new Topic();
            topic.setId(cursor.getInt(0));
            topic.setName(cursor.getString(1));
            topic.setImage(cursor.getBlob(2));
            topics.add(topic);
        }
        cursor.close();
        return topics;
    }
}
