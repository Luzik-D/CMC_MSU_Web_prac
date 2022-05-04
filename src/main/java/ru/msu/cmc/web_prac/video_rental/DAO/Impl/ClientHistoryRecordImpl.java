package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ClientHistoryRecordImpl extends AbstractDAOImpl<ClientHistoryRecord> implements ClientHistoryRecordDAO {
    public ClientHistoryRecordImpl() {
        super(ClientHistoryRecord.class);
    }

    @Override
    public List<ClientHistoryRecord> findRecord(Integer clientId, Integer copyId, String dateOfTransfer,
                                                String dateOfReceipt, String actualDateOfReceipt, Integer transferAmount) {
        try(Session session = this.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ClientHistoryRecord> query = cb.createQuery(ClientHistoryRecord.class);
            Root<ClientHistoryRecord> root = query.from(ClientHistoryRecord.class);

            // create path for dynamic filter query
            List<Predicate> predicates = new ArrayList<>();
            if(clientId != null) {
                predicates.add(cb.equal(root.get("client"), clientId));
            }
            if(copyId != null) {
                predicates.add(cb.equal(root.get("copy"), copyId));
            }
            if(dateOfTransfer != null) {
                //create LIKE expr
                String sqlDateOT = "%" + dateOfTransfer + "%";

                predicates.add(cb.like(root.get("dateOfTransfer"), sqlDateOT));
            }
            if(dateOfReceipt != null) {
                //create LIKE expr
                String sqlDateOR = "%" + dateOfReceipt + "%";

                predicates.add(cb.like(root.get("dateOfReceipt"), sqlDateOR));
            }
            if(actualDateOfReceipt != null) {
                //create LIKE expr
                String sqlADateOT = "%" + actualDateOfReceipt + "%";
                predicates.add(cb.like(root.get("actualDateOfReceipt"), sqlADateOT));
            }
            if(transferAmount != null) {
                predicates.add(cb.equal(root.get("transferAmount"), transferAmount));
            }

            //run query
            query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
            return session.createQuery(query).getResultList();
        }
    }
}
