package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientDAOImpl;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("clients", clientDAO.getAll());
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("client", clientDAO.getById(id));
        return "clients/show";
    }
}
