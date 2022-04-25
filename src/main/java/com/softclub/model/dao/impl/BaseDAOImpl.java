package com.softclub.model.dao.impl;

import com.softclub.model.dao.BaseDAO;
import com.softclub.model.entity.BaseEntity;
import com.softclub.model.util.HibernateUtil;
import com.softclub.model.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public abstract class BaseDAOImpl<P extends Serializable, E extends BaseEntity<P>> implements BaseDAO<P, E> {

    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Class<E> clazz;

    protected BaseDAOImpl() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        clazz = (Class<E>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
    }

    @Override
    public Optional<E> findById(P id) {
        try (Session session = sessionFactory.openSession()) {
            E entity = session.find(clazz, id);
            log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_DAO_BY, entity, id);
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public P save(E entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = session.save(entity);
        log.debug(LoggerUtil.ENTITY_WAS_SAVED_IN_DAO, entity);
        transaction.commit();
        session.close();
        return (P) id;
    }

    @Override
    public void update(E entity) {
        if (Objects.nonNull(entity)) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            log.debug(LoggerUtil.ENTITY_WAS_UPDATED_IN_DAO, entity);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(E entity) {
        if (Objects.nonNull(entity)) {
            try (Session session = sessionFactory.openSession()) {
                Object persistentInstance = session.load(clazz, entity.getId());
                if (persistentInstance != null) {
                    Transaction transaction = session.beginTransaction();
                    session.delete(persistentInstance);
                    log.debug(LoggerUtil.ENTITY_WAS_DELETED_IN_DAO, entity);
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<E> criteria = cb.createQuery(clazz);
            Root<E> root = criteria.from(clazz);
            List<E> resultList = session
                    .createQuery(criteria.select(root))
                    .getResultList();
            log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_DAO, resultList);
            return resultList;
        }
    }
}
