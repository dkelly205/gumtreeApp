package models;

import models.Advert;

public class Comment {

    private int id;
    private User user;
    private String text;
    private Advert advert;

    public Comment() {
    }

    public Comment(String text, User user, Advert advert) {
        this.text = text;
        this.user = user;
        this.advert = advert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
