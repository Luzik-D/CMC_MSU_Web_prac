package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.CopyDAOImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.FilmDAOImpl;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import javax.xml.ws.Service;
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

    @GetMapping("/filtered")
    public String showFiltered(Model model) {
        model.addAttribute("filter", new Film());

        return "films/filtered";
    }

    @GetMapping("films/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("film", filmDAO.getById(id));

        // get info about disks
        List<Copy> disks = copyDAO.findCopy(id, "Диск", null, null);

        //error if null
        if(disks.size() == 0) {
            //error
            return "films/show";
        }
        int disksAmount = disks.size();
        int diskPrice = disks.get(0).getPrice();

        //add to model
        model.addAttribute("disksAmount", disksAmount);
        model.addAttribute("diskPrice", diskPrice);

        //get info about cassettes
        List<Copy> cassettes = copyDAO.findCopy(id, "Кассета", null, null);

        //error if null
        if(cassettes.size() == 0) {
            //error
            return "films/show";
        }

        int cassettesAmount = cassettes.size();
        int cassettePrice = cassettes.get(0).getPrice();

        //add to model
        model.addAttribute("cassettesAmount", cassettesAmount);
        model.addAttribute("cassettePrice", cassettePrice);

        return "films/show";
        //return "/";
    }

    @GetMapping("films/new")
    public String newFilm(Model model) {
        model.addAttribute("newFilm", new Film());

        return "films/new";
    }

    @GetMapping("films/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("film", filmDAO.getById(id));

        return "films/edit";
    }
    @PostMapping("films/filtered")
    public String filter(@ModelAttribute Film film, Model model) {
        model.addAttribute("filteredFilms", filmDAO.findFilm(film.getTitle(), film.getCompany(),
                                                                         film.getDirector(), film.getYearOfRelease()));
        return "films/filtered";
    }

    @PostMapping("films/new")
    public String createFilm(@ModelAttribute("newFilm") Film film) {
        filmDAO.save(film);

        return "redirect:/";
    }

    /*
     * get all copies for film
     * it doesn't redirects to "copies" page, only shows filtered result
     */
    @PostMapping("films/{id}")
    public String getCopies(@ModelAttribute("film") Film film, Model model,
                            final RedirectAttributes redirectAttributes) {;
        List<Copy> filmCopies = copyDAO.findCopy(film.getId(), null, null, null);

        model.addAttribute("filmCopies", filmCopies);

        return "copies/filtered";
    }
    @DeleteMapping("films/{id}")
    public String deleteFilm(@PathVariable("id") Integer id) {
        filmDAO.delete(filmDAO.getById(id));

        return "redirect:/";
    }

    @PatchMapping("films/{id}")
    public String updateFilm(@ModelAttribute("film") Film film) {
        filmDAO.update(film);

        return "redirect:/";
    }
}
