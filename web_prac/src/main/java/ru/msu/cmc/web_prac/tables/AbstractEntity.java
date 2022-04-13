package ru.cs.msu.web_prac.tables;

public interface AbstractEntity<ID> {
    ID getId();
    void setId(ID id);
}