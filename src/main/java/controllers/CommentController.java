package controllers;

import db.DBHelper;
import models.Advert;
import models.Comment;
import models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class CommentController {

    public CommentController() {
        this.setupEndPoints();
    }

    private void setupEndPoints() {

        post("/adverts/:id/comment", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String text = req.queryParams("comment");
            int id = Integer.parseInt(req.params("id"));
            String username = LoginController.getLoggedInUsername(req, res);
            Customer customer = DBHelper.getLoggedInUser(username);
            Advert advert = DBHelper.find(id, Advert.class);
            Comment comment = new Comment(text, customer, advert);
            DBHelper.saveOrUpdate(comment);
            DBHelper.saveOrUpdate(advert);
            model.put("advert", advert);
            res.redirect(req.headers("referer"));
            return null;
        }, new VelocityTemplateEngine());


        post("/comments/:id/delete", (req, res) ->{
            Integer id = Integer.parseInt(req.params("id"));
            Comment comment = DBHelper.find(id, Comment.class);
            DBHelper.delete(comment);
            res.redirect(req.headers("referer"));
            return null;
        }, new VelocityTemplateEngine());
    }

}
