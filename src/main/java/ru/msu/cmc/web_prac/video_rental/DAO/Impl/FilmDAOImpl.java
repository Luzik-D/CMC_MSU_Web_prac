package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;


@Repository
@Transactional
public class FilmDAOImpl implements FilmDAO {
    //init logger to save results
    private static final Logger logger = LoggerFactory.getLogger(FilmDAOImpl.class);

    //init session factory
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory.getObject();
    }

    //methods
    @Override
    public Film getFilmById(int id) {
        try( Session session = sessionFactory.openSession()) {
            //Film film = (Film) session.load(Film.class, new Integer(id))
            Film film = session.get(Film.class, id);
            logger.info("Get film by id: " + film);

            return film;
        }
    }

    @Override
    public List<Film> getAll() {
        Session session = sessionFactory.openSession();
        List<Film> filmList = session.createQuery("SELECT title FROM Film").list();

        return filmList;
    }
}
