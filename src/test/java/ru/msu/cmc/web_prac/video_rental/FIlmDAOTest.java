package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FIlmDAOTest {
    @Autowired
    private FilmDAO filmDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getFilmById() throws Exception {
        Film film = filmDAO.getById(4);
        assertEquals("Columbia Pictures", film.getCompany());
    }

    @Test
    public void getAllFilms() throws Exception {
        List<Film> filmList = filmDAO.getAll();
        assertEquals(4, filmList.size());
    }

    // tests for filter methods
    @Test
    public void getFilmWithFilter() throws Exception {
        // should be not empty
        List<Film> filmList = filmDAO.getFilmsByTitle("Брат");
        assertNotEquals(null, filmList);

        filmList = filmDAO.getFilmsByCompany("Columbia Pictures");
        assertNotEquals(null, filmList);

        filmList = filmDAO.getFilmsByDirector("Гай Ричи");
        assertNotEquals(null, filmList);

        filmList = filmDAO.getFilmsByYear("2000");
        assertNotEquals(null, filmList);

        //should be empty
        filmList = filmDAO.getFilmsByTitle("test");
        assertEquals(null, filmList);

        filmList = filmDAO.getFilmsByCompany("work");
        assertEquals(null, filmList);

        filmList = filmDAO.getFilmsByDirector("Dmitry Luzik");
        assertEquals(null, filmList);

        filmList = filmDAO.getFilmsByYear("3001");
        assertEquals(null, filmList);
    }

}
