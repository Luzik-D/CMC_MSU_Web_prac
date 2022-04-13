package ru.cs.msu.web_prac.DAO;
import ru.cs.msu.web_prac.tables.AbstractEntity;

public interface GenericDAO<T extends AbstractEntity<ID>, ID>  {
    T getById(ID id);
}