package models;

import java.util.ArrayList;
import java.util.List;

public class Advert {

    private int id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String image;
    private String admission_date;
    private User user;
    private List<Comment> comments;
    private List<User> favouriters;

    public Advert() {
    }

    public Advert(String title, String description, double price, Category category, String image, String admission_date, User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.admission_date = admission_date;
        this.user = user;
        this.comments = new ArrayList<>();
        this.favouriters = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getFavouriters() {
        return favouriters;
    }

    public void setFavouriters(List<User> favouriters) {
        this.favouriters = favouriters;
    }

    public void addCommentToAdvert(Comment comment){
        this.comments.add(comment);
    }

    public void addUserToFavouriters(User user){
        this.favouriters.add(user);
    }


}
