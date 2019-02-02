package db;

import models .*;

import java.util.Date;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Comment.class);
        DBHelper.deleteAll(Advert.class);
        DBHelper.deleteAll(Customer.class);

        Date now = new Date();

        Customer user1 = new Customer("Danny", "password");
        Customer user2 = new Customer("Tina", "password");
        Customer user3 = new Customer("Meghan", "password");

        Advert advert1 = new Advert("Bike", "BMX", 50.00, Category.SPORTS, "n/a", now, user1);
        Advert advert2 = new Advert("Bike", "Mountain", 250.00, Category.SPORTS, "n/a", now, user1);
        Advert advert3 = new Advert("Drawers", "Chest of Drawers", 20.00, Category.HOUSEHOLD, "n/a", now, user2);
        Advert advert4 = new Advert("Car", "Renault Clio", 400.00, Category.OTHER, "n/a", now, user3);

        Comment comment1 = new Comment("Contact number?", user2, advert1);
        Comment comment2 = new Comment("Can you take 10 off the price?", user2, advert1);



        DBHelper.saveOrUpdate(user1);
        DBHelper.saveOrUpdate(user2);
        DBHelper.saveOrUpdate(user3);

        DBHelper.saveOrUpdate(advert1);
        DBHelper.saveOrUpdate(advert2);
        DBHelper.saveOrUpdate(advert3);
        DBHelper.saveOrUpdate(advert4);

        DBHelper.saveOrUpdate(comment1);
        DBHelper.saveOrUpdate(comment2);

        user1.addFavourite(advert4);
        advert4.addCustomerToFavouriters(user1);
        DBHelper.saveOrUpdate(user1);
        DBHelper.saveOrUpdate(advert4);

    }
}
