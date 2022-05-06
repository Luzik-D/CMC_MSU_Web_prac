package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Client;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientHistoryRecordDAOTest {
    @Autowired
    private ClientHistoryRecordDAO recordDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private CopyDAO copyDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getRecordById() {
        ClientHistoryRecord record = recordDAO.getById(1);

        assertNotNull(record);
        assertEquals((Integer) 1, record.getClient().getId());
    }

    @Test
    public void getAllRecords() {
        List<ClientHistoryRecord> recordList = recordDAO.getAll();
        assertNotNull(recordList);
        assertEquals(6, recordList.size());
    }

    //filter tests
    @Test
    public void testFilter() {
        //find all transfers from 02.2022 (remember reverse date)
        List<ClientHistoryRecord> recordList = recordDAO.findRecord(null, null, "2022-02", null, null, null);
        assertEquals(5, recordList.size());

        List<ClientHistoryRecord> clientList = recordDAO.findRecord(1, null, null, null, null, null);
        assertEquals(2, clientList.size());
    }

    //update, delete and save
    @Test
    public void testUpdate() {
        ClientHistoryRecord record = recordDAO.getById(1);
        record.setTransferAmount(700);
        recordDAO.update(record);
        assertEquals(700, recordDAO.getById(1).getTransferAmount());

        //return to valid data
        record.setTransferAmount(550);
        recordDAO.update(record);
        assertEquals(550, recordDAO.getById(1).getTransferAmount());
    }

    @Test
    public void testSaveAndUpdate() {
        //create new record
        Client client = clientDAO.getById(1);
        Copy copy = copyDAO.getById(1);
        ClientHistoryRecord newRecord = new ClientHistoryRecord( client, copy, "2022-05-04", "2022-05-10", "2022-05-10", 1000);
        recordDAO.save(newRecord);
        assertEquals(7, recordDAO.getAll().size());

        //delete new record
        List<ClientHistoryRecord> delRecord = recordDAO.findRecord(null, null, null, null, null, 1000);
        assertNotNull(delRecord);
        recordDAO.delete(delRecord.get(0));
        assertEquals(6, recordDAO.getAll().size());

        //delete non existing record
        newRecord = new ClientHistoryRecord( client, copy, "2022-05-04", "2022-05-10", "2022-05-10", 1000);
        recordDAO.delete(newRecord);
        assertEquals(6, recordDAO.getAll().size());
    }
}
