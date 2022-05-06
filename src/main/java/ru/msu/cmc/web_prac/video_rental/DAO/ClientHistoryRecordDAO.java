package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;

import java.util.List;

public interface ClientHistoryRecordDAO {
    public ClientHistoryRecord getById(Integer id);
    public List<ClientHistoryRecord> getAll();

    //update, delete and save
    void update(ClientHistoryRecord record);
    void delete(ClientHistoryRecord record);
    void save(ClientHistoryRecord record);

    //filter
    List<ClientHistoryRecord> findRecord(Integer clientId, Integer copyId, String dateOfTransfer,
                                         String dateOfReceipt, String actualDateOfReceipt, Integer transferAmount);
}
