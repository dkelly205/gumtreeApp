package controllers;

import db.DBHelper;
import models.Category;
import models.Customer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {


    public LoginController() {
        this.setupEndPoints();
    }

    private void setupEndPoints() {

        get("/login", (req, res) -> {  //NEW
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/login.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (req, res) -> { //NEW
            String inputtedUsername = req.queryParams("username");
            String inputtedPassword = req.queryParams("password");
            List<Customer> customers = DBHelper.getAll(Customer.class);
            for(Customer customer : customers){
                if(inputtedUsername.equals(customer.getName()) && inputtedPassword.equals(customer.getPassword())) {
                    req.session().attribute("username", inputtedUsername);
                    req.session().attribute("password", inputtedPassword);
                    res.redirect("/adverts");
                    return null;
                }
            }
            res.redirect(req.headers("referer"));
            return null;
        }, new VelocityTemplateEngine());


        get ("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect("/login");
            return null;
        }, new VelocityTemplateEngine());
    }


        public static String getLoggedInUsername(Request req, Response res){
            String username = req.session().attribute("username");
            if (username == null || username.isEmpty()) {
                res.redirect("/login");
            }
            return username;
        }





}
