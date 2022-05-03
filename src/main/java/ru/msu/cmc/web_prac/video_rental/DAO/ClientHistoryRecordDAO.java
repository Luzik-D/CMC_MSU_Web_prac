package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;

import java.util.List;

public interface ClientHistoryRecordDAO {
    public ClientHistoryRecord getById(Integer id);
    public List<ClientHistoryRecord> getAll();
}
