package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientDAOImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientHistoryRecordImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.CopyDAOImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.FilmDAOImpl;
import ru.msu.cmc.web_prac.video_rental.tables.Client;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;
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

    @Autowired
    private final FilmDAO filmDAO = new FilmDAOImpl();

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final ClientHistoryRecordDAO recordDAO = new ClientHistoryRecordImpl();

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

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("copy", copyDAO.getById(id));

        return "copies/edit";
    }

    @GetMapping("/new-{id}")
    public String newCopy(@PathVariable("id") Integer id, Model model) {
        //set film id to new copy (for thymeleaf creating url)
        Copy newCopy = new Copy();
        Film film = filmDAO.getById(id);
        newCopy.setFilm(film);
        //copyDAO.save(newCopy);
        model.addAttribute("newCopy", newCopy);

        System.out.printf("NEW copy film: " + newCopy.getFilm().getTitle() + '\n');
        System.out.printf("NEW COPY id " + newCopy.getId() + '\n');

        return "copies/new";
    }
    @PostMapping("/filtered")
    public String filtered(@ModelAttribute("film") Film film, Model model) {
        System.out.printf("DISK COPIES" + film);
        return"copies/filtered";
    }

    @PostMapping("/new-{id}")
    public String createCopy(@PathVariable("id") Integer id, @ModelAttribute("newCopy") Copy copy) {;
        copy.setFilm(filmDAO.getById(id));
        System.out.printf("copy film " + copy.getFilm().getTitle() + '\n');
        copyDAO.save(copy);

        return "redirect:/films/{id}";

    }
    @PatchMapping("/{id}")
    public String updateCopy(@PathVariable("id") Integer id, @ModelAttribute("copy") Copy copy) {
        //save film id (we can't change film_id of copy)
        Copy filmCopy = copyDAO.getById(id);
        copy.setFilm(filmCopy.getFilm());
        copyDAO.update(copy);

        return "redirect:/copies";
    }

    @DeleteMapping("/{id}")
    public String deleteCopy(@PathVariable("id") Integer id) {
        copyDAO.delete(copyDAO.getById(id));

        return "redirect:/copies";
    }

    /* * * * * * * * * * * * * * * * * *
     * Controller part for order copy  *
     * * * * * * * * * * * * * * * * * */

    @GetMapping("/order-{id}")
    public String order(@PathVariable("id") Integer id, Model model) {
        //for copy data
        model.addAttribute("orderCopy", copyDAO.getById(id));

        TransactionObject transaction = new TransactionObject();
        model.addAttribute("transaction", transaction);

        return "copies/order";
    }

    @PostMapping("/order-{id}")
    public String makeOrder(@PathVariable("id") Integer id,
                            @ModelAttribute("transaction") TransactionObject transaction) {
        List<Client> clientList = clientDAO.getClientByPhone(transaction.getClientPhone());
        if(clientList.size() == 0) {
            // no client
            return "/";
        }
        if(clientList.size() > 1) {
            // phone is not unique
            return "/";
        }

        Copy copy = copyDAO.getById(id);
        Integer amount = copy.getPrice();
        //hardcode
        ClientHistoryRecord record = new ClientHistoryRecord(
                clientList.get(0),
                copy,
                //copyDAO.getById(transaction.getCopyId()),
                transaction.getDate(),
                "2022-05-27",
                "2022-05-27",
                amount
        );

        recordDAO.save(record);

        return "redirect:/copies";
    }
}

