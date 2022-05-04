package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.CopyDAOImpl;

@Controller
@RequestMapping("/copies")
public class CopiesController {

    @Autowired
    private final CopyDAO copyDAO = new CopyDAOImpl();

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("copies", copyDAO.getAll());
        return "copies/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("copy", copyDAO.getById(id));
        return "copies/show";
    }
}
