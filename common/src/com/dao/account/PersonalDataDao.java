package com.dao.account;

import com.entity.PersonalDataEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by user on 03.08.2016.
 */
@Named
@Component
public class PersonalDataDao implements IPersonalDataDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonalDataDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    public PersonalDataEntity getPersonalData(String userName) {

        Session session = null;
        Transaction tx = null;
        PersonalDataEntity personalDataEntity = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            personalDataEntity = (PersonalDataEntity)session.createCriteria(PersonalDataEntity.class)
                    .add(Restrictions.eq("userName",userName)).uniqueResult();

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
        return personalDataEntity;
    }
}
