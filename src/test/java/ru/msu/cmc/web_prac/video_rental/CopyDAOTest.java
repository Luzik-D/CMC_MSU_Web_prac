package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyDAOTest {
    @Autowired
    private CopyDAO copyDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getCopyById() throws Exception {
        Copy copy = copyDAO.getById(2);

        assertNotEquals(null, copy);
        assertEquals(450, copy.getPrice());
    }
}
