package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.web_prac.video_rental.DAO.AbstractDAO;
import ru.msu.cmc.web_prac.video_rental.tables.AbstractTable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractDAOImpl<T extends AbstractTable> implements AbstractDAO<T> {

    //init logger
    private static final Logger logger = LoggerFactory.getLogger(AbstractDAOImpl.class);

    //init session factory
    private SessionFactory sessionFactory;

    //constructor
    private Class<T> entityClass;
    public AbstractDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    //set session factory
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory.getObject();
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Override
    public T getById(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            T entity = session.get(entityClass, id);
            logger.info("Get by id" + entityClass + id);

            return entity;
        }
    }

    @Override
    public List<T> getAll() {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root);

            List<T> entityList = session.createQuery(query).getResultList();
            logger.info("Get all records " + entityClass);

            return entityList;
        }
    }

    @Override
    public void update(T entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(T entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }
}
