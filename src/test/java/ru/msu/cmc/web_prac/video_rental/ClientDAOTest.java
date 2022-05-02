package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientDAOTest {
    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getClientById() throws Exception {
        Client client = clientDAO.getById(1);
        assertEquals("89261900909", client.getPhone());
    }
}
