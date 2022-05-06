package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.CopyDAOImpl;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
@RequestMapping("/copies")
@SessionAttributes("sendFilm")
public class CopiesController {

    @Autowired
    private final CopyDAO copyDAO = new CopyDAOImpl();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("copies", copyDAO.getAll());
        return "copies/index";
    }

    @GetMapping("/filtered")
    //public String filter(@ModelAttribute("sendFilm") Film film, Model model) {
    public String filter(Model model) {

        return "copies/filtered_cassettes";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("copy", copyDAO.getById(id));
        return "copies/show";
    }

    /*
     * returns list of cassettes by film id
     */
    @GetMapping("/filtered/cassette-{id}")
    public String filteredCassettes(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("filteredCassettes", copyDAO.findCopy(id, "Кассета", null, null));

        return "copies/filtered_cassettes";
    }

    /*
     * returns list of disks by film id
     */
    @GetMapping("/filtered/disk-{id}")
    public String filteredDisks(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("filteredDisks", copyDAO.findCopy(id, "Диск", null, null));

        return "copies/filtered_disks";
    }
    @PostMapping("/filtered")
    public String filtered(@ModelAttribute("film") Film film, Model model) {
        System.out.printf("DISK COPIES" + film);
        return"copies/filtered";
    }


}
