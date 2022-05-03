package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.AbstractDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Client;
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
}
