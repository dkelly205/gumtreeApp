package db;

import models.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.Array;
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



    public static List<Advert> getUsersFavourites(Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        session.refresh(customer);
        Hibernate.initialize(customer.getFavourites());
        session.close();
        return customer.getFavourites();
    }


    public static List<Customer> getFavouriters(Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        session.refresh(advert);
        Hibernate.initialize(advert.getFavouriters());
        session.close();
        return advert.getFavouriters();
    }


    public static void removeAdvertFromFavourites(Customer customer, Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.refresh(customer);
            List <Advert> favourites = customer.getFavourites();
            List<Advert> advertsToRemove = new ArrayList<>();
            for(Advert favourite : favourites){
                if(advert.getId() == favourite.getId()){
                    advertsToRemove.add(favourite);
                }
            }
            favourites.removeAll(advertsToRemove);
            System.out.println(customer.getFavourites().size());
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    public static void removeCustomerFromFavouriters(Customer customer, Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.refresh(advert);
            List<Customer> favouriters = advert.getFavouriters();
            List<Customer> customersToRemove = new ArrayList<>();
            for(Customer user : favouriters) {
                if (user.getId() == customer.getId()) {
                    customersToRemove.add(user);
                }
            }
            favouriters.removeAll(customersToRemove);
            System.out.print(advert.getFavouriters().size());
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void removeAllFavouriters(Advert advert){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.refresh(advert);
            List<Customer> favouriters = advert.getFavouriters();
            for(Customer customer : favouriters){
               customer.removeFavourite(advert);
            }
            advert.removeAllFavouriters();
            transaction.commit();
        }
        catch (HibernateException e) {
        transaction.rollback();
        e.printStackTrace();
        } finally {
        session.close();
        }
    }

    public static void addAdvertToFavourites(Advert advert, Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.refresh(customer);
            customer.addFavourite(advert);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void addCustomerToFavouriters(Advert advert, Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.refresh(advert);
            advert.addCustomerToFavouriters(customer);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



}
