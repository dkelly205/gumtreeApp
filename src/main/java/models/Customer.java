package models;

import models.Advert;
import models.Comment;
import org.hibernate.annotations.LazyCollection;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {

    private int id;
    private String name;
    private String password;
    private List<Advert> adverts;
    private List<Comment> comments;
    private List<Advert> favourites;

    public Customer() {
    }

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.adverts = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.favourites = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "customer", fetch=FetchType.EAGER)
    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @ManyToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="favourite_adverts",
            joinColumns = {@JoinColumn(name="customer_id", nullable=false, updatable=false)},
            inverseJoinColumns = {@JoinColumn(name="advert_id", nullable=false, updatable=false)})
    public List<Advert> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Advert> favourites) {
        this.favourites = favourites;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void addAdvert(Advert advert){
        this.adverts.add(advert);
    }

    public void removeAdvert(Advert advert){
        this.adverts.remove(advert);
    }

    public void addFavourite(Advert advert){
        this.favourites.add(advert);
    }

    public void removeFavourite(Advert advert) {
        this.favourites.remove(advert);
    }
}
