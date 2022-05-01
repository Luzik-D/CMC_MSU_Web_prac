package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

/* methods for working with table "Film" */
public interface FilmDAO {
    public Film getFilmById(int id);
    public List<Film> getAll();
}
