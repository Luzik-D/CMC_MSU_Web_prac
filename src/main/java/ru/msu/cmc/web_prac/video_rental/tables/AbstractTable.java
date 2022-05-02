package ru.msu.cmc.web_prac.video_rental.tables;

/* Create an abstract table with getter and setter for id */
public interface AbstractTable<ID> {
    ID getId();
    void setId(int ID);
}
