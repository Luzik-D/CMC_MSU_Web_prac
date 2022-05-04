package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.FilmDAOImpl;

@Controller
@RequestMapping("/films")
public class FilmsController {

    @Autowired
    private final FilmDAO filmDAO = new FilmDAOImpl();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("films", filmDAO.getAll());
        return "films/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("film", filmDAO.getById(id));
        return "films/show";
    }
}
