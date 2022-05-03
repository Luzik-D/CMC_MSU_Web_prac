package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

public interface CopyDAO {
    Copy getById(Integer id);
    public List<Copy> getAll();

    //filter methods
    List<Copy> getByFilmId(Integer filmId);
    List<Copy> getCopyByType(String type);
    List<Copy> getCopyByStatus(String status);
    List<Copy> getCopyByPrice(int price);

    Film getFilm();

    //update, delete and save
    void update(Copy copy);
    void delete(Copy copy);
    void save(Copy copy);
}
