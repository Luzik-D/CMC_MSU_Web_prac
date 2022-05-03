package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;

@Repository
@Transactional
public class ClientHistoryRecordImpl extends AbstractDAOImpl<ClientHistoryRecord> implements ClientHistoryRecordDAO {
    public ClientHistoryRecordImpl() {
        super(ClientHistoryRecord.class);
    }
}
