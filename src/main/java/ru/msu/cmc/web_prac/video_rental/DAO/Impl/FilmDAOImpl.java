package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

@Repository
@Transactional
public class FilmDAOImpl extends AbstractDAOImpl<Film> implements FilmDAO {
    public FilmDAOImpl() {
        super(Film.class);
    }

    @Override
    public List<Film> getFilmsByTitle(String title) {
        //String sql_title = "'" + title + "'";
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film WHERE title = :name", Film.class)
                    .setParameter("name", title);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Film> getFilmsByCompany(String company) {
        return null;
    }

    @Override
    public List<Film> getFilmsByDirector(String director) {
        return null;
    }

    @Override
    public List<Film> getFilmsByYear(String year) {
        return null;
    }
}
