package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="adverts")
public class Advert {

    private int id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String image;
    private Date admission_date;
    private Customer customer;
    private List<Comment> comments;
    private List<Customer> favouriters;

    public Advert() {
    }

    public Advert(String title, String description, double price, Category category, String image, Date admission_date, Customer customer) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.admission_date = admission_date;
        this.customer = customer;
        this.comments = new ArrayList<>();
        this.favouriters = new ArrayList<>();
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

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name="category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name="image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name="admission_date")
    public Date getAdmissionDate() {
        return admission_date;
    }

    public void setAdmissionDate(Date admission_date) {
        this.admission_date = admission_date;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @OneToMany(mappedBy = "advert", orphanRemoval = true)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @ManyToMany(mappedBy = "favourites", cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public List<Customer> getFavouriters() {
        return favouriters;
    }

    public void setFavouriters(List<Customer> favouriters) {
        this.favouriters = favouriters;
    }

    public void addCommentToAdvert(Comment comment){
        this.comments.add(comment);
    }

    public void addCustomerToFavouriters(Customer customer){
        this.favouriters.add(customer);
    }

    public void removeCustomerFromFavouriters(Customer customer) { this.favouriters.remove(customer);}

    public void removeAllFavouriters(){
        this.favouriters.clear();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)  return true;

        if(obj == null) return false;

        if(this.getClass() != obj.getClass()) return false;

        Advert other = (Advert) obj;

        if(this.id != other.id) return false;

        return true;


    }


}
