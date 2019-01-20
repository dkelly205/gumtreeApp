package controllers;

import db.DBHelper;
import db.Seeds;
import models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        staticFileLocation("/public");
        Seeds.seedData();
        AdvertController advertController = new AdvertController();
        LoginController loginController = new LoginController();
        UserController userController = new UserController();
        CommentController commentController = new CommentController();


        get("/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req, res); // NEW
            Customer customer = DBHelper.getLoggedInUser(loggedInUser);
            model.put("customer", customer);
            model.put("user", loggedInUser);
            model.put("template","templates/main.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }




}
