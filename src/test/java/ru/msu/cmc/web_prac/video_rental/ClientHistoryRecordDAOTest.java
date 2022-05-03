package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientHistoryRecordDAOTest {
    @Autowired
    private ClientHistoryRecordDAO clientHistoryRecordDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getRecordById() {
        ClientHistoryRecord record = clientHistoryRecordDAO.getById(1);

        assertNotEquals(null, record);
        assertEquals((Integer) 1, record.getClient().getId());
    }
}
