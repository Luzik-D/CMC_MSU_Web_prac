package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

public interface CopyDAO {
    Copy getById(Integer id);
    public List<Copy> getAll();

    //update, delete and save
    void update(Copy copy);
    void delete(Copy copy);
    void save(Copy copy);

    //filter method
    List<Copy> findCopy(Integer filmId, String type, String status, Integer price);
}
