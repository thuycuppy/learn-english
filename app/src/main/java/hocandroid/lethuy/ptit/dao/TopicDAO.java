package hocandroid.lethuy.ptit.dao;

import java.util.List;

import hocandroid.lethuy.ptit.model.Topic;

/**
 * Created by Admin on 5/2/2017.
 */

public interface TopicDAO {
    List<Topic> findAll();
}
