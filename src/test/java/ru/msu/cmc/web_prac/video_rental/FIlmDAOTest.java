package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FIlmDAOTest {
    @Qualifier("filmDAOImpl")
    @Autowired
    private FilmDAO filmDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void simpleTest() throws Exception {
        Film film = filmDAO.getFilmById(4);
        assertEquals("Columbia Pictures", film.getCompany());
    }

}
