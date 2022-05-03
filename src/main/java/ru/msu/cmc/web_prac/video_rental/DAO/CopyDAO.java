package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Copy;

import java.util.List;

public interface CopyDAO {
    Copy getById(int id);
    public List<Copy> getAll();
    void update(Copy copy);
    void delete(Copy copy);
    void save(Copy copy);
}
