package controllers;

import db.DBHelper;
import models.Advert;
import models.Customer;
import models.Category;
import models.Comment;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class AdvertController {

    public AdvertController() {
        this.setupEndPoints();
    }

    private void setupEndPoints() {
        get("/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Advert> adverts = DBHelper.getAll(Advert.class);
            List<Category> categories = DBHelper.getAllCategories();
            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            Customer customer = DBHelper.getLoggedInUser(loggedInUser);
            System.out.println(customer.getName());
            model.put("categories", categories);
            model.put("customer", customer);
            model.put("adverts", adverts);
            model.put("user", loggedInUser);
            model.put("login", "templates/login.vtl");
            model.put("template", "templates/adverts/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/adverts/create", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Category> categories = DBHelper.getAllCategories();
            String loggedInUser = LoginController.getLoggedInUsername(req, res); // NEW
            model.put("user", loggedInUser);
            model.put("categories", categories);
            Customer customer = DBHelper.getLoggedInUser(loggedInUser);
            model.put("customer", customer);
            model.put("template", "templates/adverts/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("adverts/:id", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(id, Advert.class);
            List<Comment> comments = DBHelper.getCommentsInAdvert(advert);
            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            Customer customer = DBHelper.getLoggedInUser(loggedInUser);
            model.put("customer", customer);
            model.put("comments", comments);
            model.put("user", loggedInUser);
            model.put("advert", advert);
            model.put("template", "templates/adverts/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/adverts", (req, res) -> {
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            double price = Double.parseDouble(req.queryParams("price"));
            Category category = Category.valueOf(req.queryParams("category"));
            String image = req.queryParams("image");
            String admission_date = req.queryParams("admission_date");
            Customer customer = DBHelper.getLoggedInUser(LoginController.getLoggedInUsername(req, res));
            Advert advert = new Advert(title, description, price, category, image, admission_date, customer);
            DBHelper.saveOrUpdate(advert);
            customer.addAdvert(advert);
            DBHelper.saveOrUpdate(customer);
            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());


        get("/adverts/:id/delete", (req,res) ->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(id, Advert.class);
            String loggedInUser = LoginController.getLoggedInUsername(req, res); // NEW
            model.put("user", loggedInUser);
            model.put("advert", advert);
            model.put("template", "templates/adverts/delete.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/adverts/:id/delete", (req,res) ->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(id, Advert.class);
            DBHelper.delete(advert);
            Customer customer = DBHelper.getLoggedInUser(LoginController.getLoggedInUsername(req,res));
            customer.removeAdvert(advert);
            DBHelper.saveOrUpdate(customer);
            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());







    }
}
