package com.dao.flats;

import com.entity.FlatsEntity;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 08.08.2016.
 */
@Named
@Component
public class FlatDao implements FlatDaoImpl{

    private SessionFactory sessionFactory;

    private Criteria getCriteriaFlats(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(FlatsEntity.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operationOut"));
    }

    @Autowired
    public FlatDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }


    @Override
    public List getFlats(List flatsIdList) {
        Session session = null;
        Transaction tx = null;
        List  flatsEntityList = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            flatsEntityList = getCriteriaFlats(session)
                    .add(Restrictions.in("id",flatsIdList))
                    .list();


            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return flatsEntityList;
    }

    @Override
    public Object getFlat(Long flatId) {
        Session session = null;
        Transaction tx = null;
        Object  flat = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            flat = getCriteriaFlats(session)
                    .add(Restrictions.eq("id",flatId))
                    .uniqueResult();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return flat;
    }

    public List getFlats(String userName) {
        Session session = null;
        Transaction tx = null;
        List  flats = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            flats = getCriteriaFlats(session)
                    .createAlias("operationIn","operIn")
                    .add(Restrictions.eq("operIn.userName",userName))
                    .list();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return flats;
    }
}
