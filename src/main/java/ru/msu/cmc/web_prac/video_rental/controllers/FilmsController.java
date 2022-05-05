package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.CopyDAOImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.FilmDAOImpl;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

@Controller
@RequestMapping()
public class FilmsController {

    @Autowired
    private final FilmDAO filmDAO = new FilmDAOImpl();

    @Autowired
    private final CopyDAO copyDAO = new CopyDAOImpl();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("films", filmDAO.getAll());
        model.addAttribute("filter", new Film());
        return "films/index";
    }

    @GetMapping("films/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("film", filmDAO.getById(id));

        // get info about disks
        List<Copy> disks = copyDAO.findCopy(id, "Диск", null, null);

        //error if null
        if(disks == null) {
            //error
        }

        int disksAmount = disks.size();
        int diskPrice = disks.get(0).getPrice();

        //add to model
        model.addAttribute("disksAmount", disksAmount);
        model.addAttribute("diskPrice", diskPrice);

        //get info about cassettes
        List<Copy> cassettes = copyDAO.findCopy(id, "Кассета", null, null);

        //error if null
        if(cassettes == null) {
            //error
        }

        int cassettesAmount = cassettes.size();
        int cassettePrice = cassettes.get(0).getPrice();

        //add to model
        model.addAttribute("cassettesAmount", cassettesAmount);
        model.addAttribute("cassettePrice", cassettePrice);
        return "films/show";
    }

    @PostMapping()
    public String filter(@ModelAttribute Film film, Model model) {
        model.addAttribute("filteredFilms", filmDAO.findFilm(film.getTitle(), film.getCompany(),
                                                                         film.getDirector(), film.getYearOfRelease()));
        return "films/filtered";
    }
}
