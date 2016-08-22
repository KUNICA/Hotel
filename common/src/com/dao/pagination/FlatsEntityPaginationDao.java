package com.dao.pagination;

import com.entity.FlatsEntity;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 04.08.2016.
 */
@Named
public class FlatsEntityPaginationDao implements FlatsEntityPaginationDaoImpl {

    private SessionFactory sessionFactory;

    private Criteria getCriteriaFlats(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(FlatsEntity.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operationOut"));
    }

    @Autowired
    public FlatsEntityPaginationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    public List getObjects(int start, int end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms) {

        Session session = null;
        Transaction tx = null;
        List  flatsEntityList = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            session.enableFetchProfile("mediaProfile");
            Query query = session.createQuery("from FlatsEntity flat where flat.operationOut is null and " +
                    "flat.price >= :priceMin and flat.price <= :priceMax and " +
                    "flat.slleeps <= :persons and " +
                    "flat.countRoums <= :badrooms and " +
                    "flat.bathrooms <= :bathrooms")
                    .setFirstResult(start)
                    .setMaxResults(end);

            query.setDouble("priceMin", minPrice);
            query.setDouble("priceMax", maxPrice);
            query.setLong("persons",persons);
            query.setLong("badrooms",badrooms);
            query.setLong("bathrooms",bathrooms);

            flatsEntityList = query.list();

           // flatsEntityList = getCriteriaFlats(session)
             //       .setFirstResult(start)
               //     .setMaxResults(end)
                 //   .list();

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
    public Long getCountObjects(int start, int end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms) {
        Session session = null;
        Transaction tx = null;
        Long count =0L;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            count = (Long)getCriteriaFlats(session)
                    .add(Restrictions.ge("price",minPrice))
                    .add(Restrictions.le("price",maxPrice))
                    .add(Restrictions.le("slleeps",persons))
                    .add(Restrictions.le("countRoums",badrooms))
                    .add(Restrictions.le("bathrooms",bathrooms))
                    .setProjection(Projections.rowCount()).uniqueResult();
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
        return count;
    }

    @Override
    public Object getCountMin(String field) {
        Session session = null;
        Transaction tx = null;
        Object count =0f;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            count = getCriteriaFlats(session)
                    .setProjection(Projections.min(field))
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
        return count;
    }

    @Override
    public Object getCountMax(String field) {
        Session session = null;
        Transaction tx = null;
        Object count = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            count = getCriteriaFlats(session)
                    .setProjection(Projections.max(field))
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
        return count;
    }

}
