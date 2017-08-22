package hocandroid.lethuy.ptit.dao;

import java.util.List;

import hocandroid.lethuy.ptit.model.Test;

/**
 * Created by Admin on 5/10/2017.
 */

public interface TestDAO {
    List<Test> findAll(int id_grammar);
}
