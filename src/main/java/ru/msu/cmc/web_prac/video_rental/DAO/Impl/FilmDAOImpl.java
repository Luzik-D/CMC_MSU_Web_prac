package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

@Repository
@Transactional
public class FilmDAOImpl extends AbstractDAOImpl<Film> implements FilmDAO {
    public FilmDAOImpl() {
        super(Film.class);
    }
}
