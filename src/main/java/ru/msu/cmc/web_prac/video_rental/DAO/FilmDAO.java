package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

/* methods for working with table "Film" */
public interface FilmDAO {
    Film getById(Integer id);
    public List<Film> getAll();

    void update(Film film);
    void delete(Film film);
    void save(Film film);

    //methods for filter
    List<Film> getFilmsByTitle(String title);
    List<Film> getFilmsByCompany(String company);
    List<Film> getFilmsByDirector(String director);
    List<Film> getFilmsByYear(String year);

    List<Film> findFilm(String title, String company, String director, String year);
}
