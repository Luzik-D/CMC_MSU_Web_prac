package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

import static org.junit.Assert.*;

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
    // ! legacy (didn't know about dynamic query builder) !
    // ! see filter tests below
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

    //test update, delete and save
    @Test
    public void testUpdate() {
        Film testFilm = filmDAO.getById(1);
        testFilm.setTitle("Брат 3");
        filmDAO.update(testFilm);
        testFilm = filmDAO.getById(1);
        assertEquals("Брат 3", testFilm.getTitle());

        //return to correct data
        testFilm.setTitle("Брат");
        filmDAO.update(testFilm);
        testFilm = filmDAO.getById(1);
        assertEquals("Брат", testFilm.getTitle());
    }

    @Test
    public void testSaveDelete() {
        //create new film
        Film newFilm = new Film( "web", "www", "me", "2022", "descr");
        filmDAO.save(newFilm);
        List<Film> testSave = filmDAO.getFilmsByTitle("web");
        assertEquals(1, testSave.size());
        assertEquals("web", testSave.get(0).getTitle());

        //delete new film
        List<Film> deleteFilm = filmDAO.getFilmsByTitle("web");
        assertEquals(1, deleteFilm.size());
        filmDAO.delete(deleteFilm.get(0));
        List<Film> testDelete = filmDAO.getFilmsByTitle("web");
        assertEquals(null,  testDelete);

    }

    //test filter
    @Test
    public void testFilter() {
        List<Film> listFilm = filmDAO.findFilm("Брат 2", null, null, null);
        assertNotNull(listFilm);

        List<Film> grFilms = filmDAO.findFilm(null, null, "Гай Ричи", null);
        assertEquals(2, grFilms.size());

        List<Film> year = filmDAO.findFilm(null, null, null, "2000");
        assertEquals(2, year.size());

        //same director, different years
        List<Film> brother = filmDAO.findFilm(null, null, "Алексей Балабанов", "1997");
        assertEquals("Брат", brother.get(0).getTitle());
    }
}
