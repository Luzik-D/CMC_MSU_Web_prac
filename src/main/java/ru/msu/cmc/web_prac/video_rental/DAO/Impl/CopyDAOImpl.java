package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CopyDAOImpl extends AbstractDAOImpl<Copy> implements CopyDAO {
    public CopyDAOImpl() {
        super(Copy.class);
    }

    @Override
    public List<Copy> findCopy(Integer filmId, String type, String status, Integer price) {
        try(Session session = this.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Copy> query = cb.createQuery(Copy.class);
            Root<Copy> root = query.from(Copy.class);

            // create path for dynamic filter query
            List<Predicate> predicates = new ArrayList<>();
            if (filmId != null) {
                predicates.add(cb.equal(root.get("film"), filmId));
            }
            if (type != null && type != "") {
                predicates.add(cb.equal(root.get("type"), type));
            }
            if(status != null && type != "") {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (price != null) {
                predicates.add(cb.equal(root.get("price"), price));
            }

            //run query
            query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
            return session.createQuery(query).getResultList();
        }
    }
}
