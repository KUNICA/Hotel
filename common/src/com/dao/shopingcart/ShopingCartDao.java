package com.dao.shopingcart;

import com.entity.FlatsEntity;
import com.entity.ShoppingCartEntity;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06.08.2016.
 */
@Named
@Component
public class ShopingCartDao implements ShopingCartDaoIml {

    private SessionFactory sessionFactory;

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    private Criteria getCriteria(Session session, long flatId){
        return session.createCriteria(ShoppingCartEntity.class)
                .add(Restrictions.eq("flatId",flatId));
    }


    @Autowired
    public ShopingCartDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }


    public boolean isFlatCheck(long flatId){
        Session session = null;
        Transaction tx = null;
        Object shopingCart = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            shopingCart = getCriteria(session, flatId)
                    .add(Restrictions.isNotNull("check"))
                    .add(Restrictions.isNull("actual"))
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
        return shopingCart != null;
    }

    public Object getShoppingCart(String userName,long flatId){
        Session session = null;
        Transaction tx = null;
        Object shopingCart = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            shopingCart = getCriteria(session, flatId)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.isNull("actual"))
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
        return shopingCart;
    }

    public List getSumShoppingCart(String userName){
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();

        try {
            session = currentSession();
            tx = session.beginTransaction();
            session.enableFetchProfile("mediaProfile");
            DetachedCriteria dc = DetachedCriteria.forClass(ShoppingCartEntity.class)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.isNull("actual"))
                    .add(Restrictions.isNotNull("check"))
                    .setProjection(Projections.property("flatId"));

            list =  session.createCriteria(FlatsEntity.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Subqueries.propertyIn("id", dc)).list();

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
        return list;
    }

    public List getShoppingCarts(String userName){
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();

        try {
            session = currentSession();
            tx = session.beginTransaction();
            list =  session.createCriteria(ShoppingCartEntity.class)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.isNull("actual"))
                    .add(Restrictions.isNotNull("check")).list();

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
        return list;
    }

}
