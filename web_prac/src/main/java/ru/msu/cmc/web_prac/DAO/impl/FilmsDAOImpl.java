package ru.cs.msu.web_prac.DAO.impl;

import ru.cs.msu.web_prac.DAO.FilmsDAO;
import ru.cs.msu.web_prac.tables.Films;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FilmsDAOImpl extends AbstractDAOImpl<Films, Long> implements FilmsDAO {
    public FilmsDAOImpl() {
        super(Films.class);
    }
}