package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientDAOImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientHistoryRecordImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.CopyDAOImpl;
import ru.msu.cmc.web_prac.video_rental.tables.Client;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final ClientHistoryRecordDAO recordDAO = new ClientHistoryRecordImpl();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("clients", clientDAO.getAll());
        model.addAttribute("filter", new Client());
        System.out.printf("hello world\n");
        return "clients/index";
    }

    @GetMapping("/filtered")
    public String showFiltered(Model model) {
        model.addAttribute("filter", new Client());
        return "clients/filtered";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("client", clientDAO.getById(id));

        //find all copies that client had from transaction history
        List<ClientHistoryRecord> records = recordDAO.findRecord(id, null, null,
                                null, null, null);

        if(records == null) {
            //client hasn't any record
            System.out.println("client hasn't any record");
        }
        model.addAttribute("records", records);

        return "clients/show";
    }

    @GetMapping("/new")
    public String clientForm(Model model) {
        model.addAttribute("newClient", new Client());
        return "clients/new";
    }

    @PostMapping("/filtered")
    public String filter(@ModelAttribute Client client, Model model) {
        System.out.printf("hello filter");
        model.addAttribute("filteredClients", clientDAO.findClient(client.getName(), client.getPhone(), client.getAddress()));

        return "clients/filtered";
    }

    @PostMapping("/new")
    public String createClient(@ModelAttribute("newClient") Client client) {

        System.out.printf("hello new client");
        System.out.println("name" + client.getName());
        clientDAO.save(client);
        System.out.println("name" + client.getName());

        return "redirect:/clients";
    }
}
