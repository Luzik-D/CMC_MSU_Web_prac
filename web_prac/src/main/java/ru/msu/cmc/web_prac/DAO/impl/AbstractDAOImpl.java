package ru.cs.msu.web_prac.DAO.impl;

import ru.cs.msu.web_prac.DAO.GenericDAO;
import ru.cs.msu.web_prac.tables.AbstractEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public abstract class AbstractDAOImpl<T extends AbstractEntity<ID>, ID extends Serializable> implements GenericDAO<T, ID> {
    protected SessionFactory sessionFactory;

    protected Class<T> persistentClass;

    public AbstractDAOImpl(Class<T> entityClass) {
        this.persistentClass = entityClass;
    }

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory.getObject();
    }

    @Override
    public T getById(ID id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(persistentClass, id);
        }
    }
}