package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Client;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    public void getAllClients() throws Exception {
        List<Client> listClient = clientDAO.getAll();
        assertEquals(5, listClient.size());
    }

    /* filter methods tests */
    @Test
    public void getClientWithFilter() throws Exception {
        /* should be not empty */
        //get by name
        List<Client> nameClient = clientDAO.getClientByName("Дмитрий");
        assertNotEquals(null, nameClient);
        assertEquals(2, nameClient.size());
        //get by full name
        nameClient = clientDAO.getClientByName("Иванов Алексей Михайлович");
        assertNotEquals(null, nameClient);
        assertEquals(1, nameClient.size());

        //get by phone
        List<Client> phoneClient = clientDAO.getClientByPhone("89261900909");
        assertNotEquals(null, phoneClient);
        assertEquals("Иванов Алексей Михайлович", phoneClient.get(0).getName());

        //get by address
        List<Client> addressClient = clientDAO.getClientByAddress("г. Москва, ул. 2-я Владимирская, д. 37");
        assertNotEquals(null, addressClient);
        addressClient = clientDAO.getClientByAddress("Москва");
        assertNotEquals(null, addressClient);
        assertEquals(5, addressClient.size());

        /* should be empty */
        //get by name
        List<Client> emptyList = clientDAO.getClientByName("test");
        assertEquals(null, emptyList);
        emptyList = clientDAO.getClientByName("Иванов Иван Иванович");
        assertEquals(null, emptyList);

        //get by phone
        List<Client> phone = clientDAO.getClientByPhone("89261900900");
        assertEquals(null, phone);

        //get by address
        List<Client> badClient = clientDAO.getClientByAddress("Нью-Джерси");
        assertEquals(null, badClient);
    }

    // tests for update, delete and save
    @Test
    public void testUpdate() {
        Client client = clientDAO.getById(1);
        client.setPhone("111");
        clientDAO.update(client);
        client = clientDAO.getById(1);
        assertEquals("111", client.getPhone());

        //return to valid data
        client.setPhone("89261900909");
        clientDAO.update(client);
        assertNotEquals("111", client.getPhone());
    }

    @Test
    public void testSaveAndDelete() {
        //create new client
        Client client = new Client( "Viktor", "89246576123", "Moscow");
        clientDAO.save(client);
        assertEquals("Viktor", clientDAO.getClientByName("Viktor").get(0).getName());

        //delete new client
        Client delClient = clientDAO.getClientByName("Viktor").get(0);
        clientDAO.delete(delClient);
        List<Client> afterDel = clientDAO.getClientByName("Viktor");
        assertEquals(null, afterDel);

        //try to delete non exist client
        client = new Client("Viktor", "123", "test");
        clientDAO.delete(client);
        afterDel = clientDAO.getAll();
        assertEquals(5, afterDel.size());

    }
}
