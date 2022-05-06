package ru.msu.cmc.web_prac.video_rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientHistoryRecordDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientDAOImpl;
import ru.msu.cmc.web_prac.video_rental.DAO.Impl.ClientHistoryRecordImpl;
import ru.msu.cmc.web_prac.video_rental.tables.Client;
import ru.msu.cmc.web_prac.video_rental.tables.ClientHistoryRecord;

import java.util.List;

@Controller
@RequestMapping("/records")
public class RecordsController {

    @Autowired
    private final ClientHistoryRecordDAO recordDAO = new ClientHistoryRecordImpl();

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();
    @GetMapping()
    public String searchForm(Model model) {
        model.addAttribute("record", new Client());
        model.addAttribute("transactionDate", new String[8]); //field for transaction date

        return "/records/search_form";
    }

    @GetMapping("/client-{id}")
    public String showClientRecords(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("clientRecords", recordDAO.findRecord(id, null, null, null,
                                                                      null, null));
        return "records/show";
    }
    @PostMapping("/client-{id}")
    public String getClientRecords(@PathVariable("id") Integer id,
                                   @ModelAttribute("record") ClientHistoryRecord clientRecords,
                                   @ModelAttribute("transactionDate") String date) {

        return "records/show";
    }
}
