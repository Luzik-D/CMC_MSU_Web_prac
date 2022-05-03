package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

/* methods for working with table "Film" */
public interface FilmDAO {
    Film getById(int id);
    public List<Film> getAll();

    void update(Film film);
    void delete(Film film);
    void save(Film film);
}
