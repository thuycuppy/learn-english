package hocandroid.lethuy.ptit.dao;

import java.util.List;

import hocandroid.lethuy.ptit.model.Vocabulary;

/**
 * Created by Admin on 5/2/2017.
 */

public interface VocabularyDAO {
    Vocabulary findOne(int id);

    List<Vocabulary> findTopic(int id);

    int count();
}
