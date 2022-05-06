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

import java.util.ArrayList;
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

        return "/records/search_form";
    }

    @GetMapping("/client-{id}")
    public String showClientRecords(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("clientRecords", recordDAO.findRecord(id, null, null, null,
                                                                      null, null));
        return "records/show";
    }

    /*
     * Form for searching client transaction records
     * into address field sends date of transaction
     */
    @PostMapping()
    public String getClientRecords(@ModelAttribute("record") Client client, Model model) {
        List<ClientHistoryRecord> recordList = new ArrayList<>();
        //get clients by name
        List<Client> clientList = clientDAO.findClient(client.getName(), client.getPhone(), null);
        if(clientList.size() == 0) {
            //no clients
            return "/";
        }
        for(int i = 0; i < clientList.size(); i++) {
            recordList.addAll(recordDAO.findRecord(clientList.get(i).getId(), null, client.getAddress(), null, null, null));
        }
        System.out.printf("found records " + recordList.size());
        for(int i = 0; i < recordList.size(); i++) {
            System.out.printf("client " + recordList.get(i).getClient().getName() + "\n");
        }


        model.addAttribute("filteredRecords", recordList);

        return "records/filtered";
    }
}
