package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film WHERE company = :name", Film.class)
                    .setParameter("name", company);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Film> getFilmsByDirector(String director) {
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film WHERE director = :name", Film.class)
                    .setParameter("name", director);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Film> getFilmsByYear(String year) {
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film WHERE yearOfRelease = :name", Film.class)
                    .setParameter("name", year);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Film> findFilm(String title, String company, String director, String year) {
        try(Session session = this.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Film> query = cb.createQuery(Film.class);
            Root<Film> root = query.from(Film.class);

            // create path for dynamic filter query
            List<Predicate> predicates = new ArrayList<>();
            if(title != null) {
                predicates.add(cb.equal(root.get("title"), title));
            }
            if(company != null) {
                predicates.add(cb.equal(root.get("company"), company));
            }
            if(director != null) {
                predicates.add(cb.equal(root.get("director"), director));
            }
            if(year != null) {
                predicates.add(cb.equal(root.get("yearOfRelease"), year));
            }

            //run query
            query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

            return session.createQuery(query).getResultList();
        }
    }
}
