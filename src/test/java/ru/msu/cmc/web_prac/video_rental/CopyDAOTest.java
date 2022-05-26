package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyDAOTest {
    @Autowired
    private CopyDAO copyDAO;

    @Autowired
    private  FilmDAO fIlmDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getCopyById() throws Exception {
        Copy copy = copyDAO.getById(2);

        assertNotNull(copy);
        assertEquals(Optional.of(450), copy.getPrice());
    }

    @Test
    public void getAllCopy() {
        List<Copy> copyList = copyDAO.getAll();
        assertEquals(16, copyList.size());
    }

    //filter test
    @Test
    public void testFilter() {
        //find all disks
        List<Copy> copyList = copyDAO.findCopy(null, "Диск", null, null);
        assertEquals(10, copyList.size());
        List<Copy> badList = copyDAO.findCopy(null, "sdfsd", null, null);
        assertEquals(badList.size(), 0);

        //find all cassettes with film Brother
        List<Copy> brotherList = copyDAO.findCopy(1, "Диск", null, null);
        assertEquals(2, brotherList.size());

        //find all free disks with price 700
        copyList = copyDAO.findCopy(null, "Диск", "Свободный", 700);
        assertEquals(2, copyList.size());
    }

    //update, delete and save
    @Test
    public void testUpdate() {
        Copy copy = copyDAO.getById(1);
        copy.setPrice(1000);
        copyDAO.update(copy);
        copy = copyDAO.getById(1);
        assertEquals(Optional.of(1000), copy.getPrice());

        //return to valid data
        copy.setPrice(550);
        copyDAO.update(copy);
        assertEquals(Optional.of(550), copy.getPrice());
    }

    @Test
    public void testSaveAndDelete() {
        //create new copy
        Film film = fIlmDAO.getById(1);
        Copy copy = new Copy(film, "disk", "free", 100);
        copyDAO.save(copy);
        assertEquals(17, copyDAO.getAll().size());

        //delete new copy
        List<Copy> delCopy = copyDAO.findCopy(null, "disk", null, null);
        assertNotNull(delCopy);
        copyDAO.delete(delCopy.get(0));
        assertEquals(16, copyDAO.getAll().size());

        //delete non existing copy
        copy = new Copy(film, "disk", "free", 100);
        copyDAO.delete(copy);
        assertEquals(16, copyDAO.getAll().size());
    }
}
