package controllers;

import db.DBHelper;
import models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
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
            model.put("template", "templates/users/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("users/:id/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/adverts.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());





    }



}
