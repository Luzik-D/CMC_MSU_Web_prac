package ru.msu.cmc.web_prac.video_rental.DAO;

import ru.msu.cmc.web_prac.video_rental.tables.Client;

import java.util.List;

public interface ClientDAO {
    public Client getById(Integer id);
    public List<Client> getAll();
}
