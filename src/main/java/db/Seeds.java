package db;

import models .*;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Comment.class);
        DBHelper.deleteAll(Advert.class);
        DBHelper.deleteAll(User.class);

        User user1 = new User("Danny");
        User user2 = new User("Tina");

        Advert advert1 = new Advert("Bike", "BMX", 50.00, Category.SPORTS, "n/a", "01/06/2018", user1);
        Advert advert2 = new Advert("Bike", "Mountain", 250.00, Category.SPORTS, "n/a", "01/06/2018", user1);

        Comment comment1 = new Comment("Contact number?", user2, advert1);
        Comment comment2 = new Comment("Can you take 10 off the price?", user2, advert1);

        DBHelper.saveOrUpdate(user1);
        DBHelper.saveOrUpdate(user2);

        DBHelper.saveOrUpdate(advert1);
        DBHelper.saveOrUpdate(advert2);

        DBHelper.saveOrUpdate(comment1);
        DBHelper.saveOrUpdate(comment2);
    }
}
