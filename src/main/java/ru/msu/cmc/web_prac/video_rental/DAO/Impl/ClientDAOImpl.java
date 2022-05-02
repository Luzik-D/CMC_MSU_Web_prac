package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.DAO.AbstractDAO;
import ru.msu.cmc.web_prac.video_rental.DAO.ClientDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Client;

@Repository
@Transactional
public class ClientDAOImpl extends AbstractDAOImpl<Client, Integer> implements ClientDAO {
    public ClientDAOImpl() {
        super(Client.class);
    }
}
