package ru.msu.cmc.web_prac.video_rental.DAO;

import org.aspectj.apache.bcel.generic.InstructionConstants;
import ru.msu.cmc.web_prac.video_rental.tables.Client;

import java.util.List;

public interface ClientDAO {
    Client getById(Integer id);
    List<Client> getAll();

    //filter methods (Legacy)
    List<Client> getClientByName(String name);

    List<Client> getClientByPhone(String phone);

    List<Client> getClientByAddress(String address);

    //update, delete and save
    void update(Client client);
    void delete(Client client);
    void save(Client client);

    List<Client> findClient(String name, String phone, String address);
}
