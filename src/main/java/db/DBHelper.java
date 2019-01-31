package db;

import models.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHelper {


    private static Transaction transaction;
    private static Session session;

    public static void saveOrUpdate(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria criteria) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUnique(Criteria criteria) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> void deleteAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results) {
                session.delete(result);
            }
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getList(cr);
    }

    public static <T> T find(int id, Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.eq("id", id));
        return getUnique(cr);
    }


    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Collections.addAll(categories, Category.values());
        return categories;
    }

    public static Customer getLoggedInUser(String username) {
        session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = null;
        Criteria cr = session.createCriteria(Customer.class);
        cr.add(Restrictions.eq("name", username));
        customer = getUnique(cr);
        return customer;
    }

    public static List<Advert> getUsersAdverts(Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> adverts = new ArrayList<>();
        Criteria cr = session.createCriteria(Advert.class);
        cr.add(Restrictions.eq("customer", customer));
        adverts = getList(cr);
        return adverts;
    }

    public static List<Comment> getCommentsInAdvert(Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> comments = new ArrayList<>();
        Criteria cr = session.createCriteria(Comment.class);
        cr.add(Restrictions.eq("advert", advert));
        comments = getList(cr);
        return comments;
    }


    //method to get users favourites -many to many
    public static List<Advert> getUsersFavourites(Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        session.refresh(customer);
        Hibernate.initialize(customer.getFavourites());
        session.close();
        return customer.getFavourites();
    }

    //method to get favouriters -many to many
    public static List<Customer> getFavouriters(Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        session.refresh(advert);
        Hibernate.initialize(advert.getFavouriters());
        session.close();
        return advert.getFavouriters();
    }

    //method to remove users favourite - many to many
    public static void removeAdvertFromFavourites(Customer customer, Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        session.refresh(customer);
        customer.removeFavourite(advert);
        session.close();
        saveOrUpdate(customer);
    }

    //method to get users who have favourited the advert - many to many




}
