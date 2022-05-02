package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Copy;

import java.util.List;

public interface CopyDAO {
    public Copy getById(int id);
    public List<Copy> getAll();
}
