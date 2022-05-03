package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.AbstractTable;

import java.util.List;

/* all DAO classes have same methods */
public interface AbstractDAO<T extends AbstractTable> {
    T getById(int id);
    List<T> getAll();

    void update(T entity);
    void delete(T entity);
    void save(T entity);
}
