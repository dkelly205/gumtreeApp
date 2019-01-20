package controllers;

import db.DBHelper;
import models.Advert;
import models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        get("/users/register", (req,res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/users", (req,res) -> {
            String name = req.queryParams("username");
            String password = req.queryParams("password");
            Customer customer = new Customer(name, password);
            DBHelper.saveOrUpdate(customer);
            res.redirect("/login");
            return null;
        }, new VelocityTemplateEngine());

        get("users/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Customer customer = DBHelper.find(id, Customer.class);
            String loggedInUser = LoginController.getLoggedInUsername(req, res); // NEW
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("template", "templates/users/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("users/:id/edit", (req,res) ->{
            int id = Integer.parseInt(req.params(":id"));
            Customer customer = DBHelper.find(id, Customer.class);
            String name = req.queryParams("username");
            String password = req.queryParams("password");
            customer.setName(name);
            customer.setPassword(password);
            DBHelper.saveOrUpdate(customer);
            System.out.println(customer.getName());
            req.session().removeAttribute("username");
            req.session().attribute("username", customer.getName());
            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());


        get("users/:id/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Customer customer = DBHelper.find(id, Customer.class);
            List<Advert> adverts = DBHelper.getUsersAdverts(customer);
            model.put("adverts", adverts);
            String loggedInUser = LoginController.getLoggedInUsername(req, res); // NEW
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("template", "templates/users/adverts.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("users/:id/favourites", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Customer customer = DBHelper.find(id, Customer.class);
            List<Advert> adverts = customer.getFavourites();
            model.put("adverts", adverts);
            String loggedInUser = LoginController.getLoggedInUsername(req, res); // NEW
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("template", "templates/users/favourites.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());





    }



}
