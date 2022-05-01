package ru.msu.cmc.web_prac.video_rental.service;

import org.springframework.stereotype.Service;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.transaction.Transactional;
import java.util.List;

/* Generate methods for user */
@Service
public class FilmService implements FilmDAO {
    private FilmDAO filmDAO;

    public void setFilmDAO(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    @Transactional
    public Film getFilmById(int id) {
        return this.filmDAO.getFilmById(id);
    }

    @Override
    @Transactional
    public List<Film> getAll() {
        return this.filmDAO.getAll();
    }
}
