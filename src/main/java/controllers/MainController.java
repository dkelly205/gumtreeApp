package controllers;

import db.Seeds;

import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        staticFileLocation("/public");
        Seeds.seedData();
        AdvertController advertController = new AdvertController();
        LoginController loginController = new LoginController();
        UserController userController = new UserController();

    }


}
