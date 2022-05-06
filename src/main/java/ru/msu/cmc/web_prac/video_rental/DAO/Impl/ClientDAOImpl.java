package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.AbstractDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Client;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ClientDAOImpl extends AbstractDAOImpl<Client> implements ClientDAO {
    public ClientDAOImpl() {
        super(Client.class);
    }

    @Override
    public List<Client> getClientByName(String name) {
        // make LIKE expr to find by parts of name
        String sqlName = "%" + name + "%";
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery("FROM Client WHERE name LIKE :name", Client.class)
                    .setParameter("name", sqlName);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Client> getClientByPhone(String phone) {
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery("FROM Client WHERE phone = :phone", Client.class)
                    .setParameter("phone", phone);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Client> getClientByAddress(String address) {
        // make LIKE expr to find by parts of address
        String sqlAddress = "%" + address + "%";
        try(Session session = this.getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery("FROM Client WHERE address LIKE :address", Client.class)
                    .setParameter("address", sqlAddress);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Client> findClient(String name, String phone, String address) {
        try(Session session = this.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);

            // create path for dynamic filter query
            List<Predicate> predicates = new ArrayList<>();
            if(name != null && name != "") {
                //create LIKE expr
                String sqlName = "%" + name + "%";

                predicates.add(cb.like(root.get("name"), sqlName));
            }
            if(phone != null && phone != "") {
                predicates.add(cb.equal(root.get("phone"), phone));
            }
            if(address != null && address != "") {
                //create LIKE expr
                String sqlAddress = "%" + address + "%";

                predicates.add(cb.like(root.get("address"), sqlAddress));
            }

            //run query
            query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
            return session.createQuery(query).getResultList();
        }
    }
}
