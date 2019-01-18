package models;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {

    private int id;
    private Customer customer;
    private String text;
    private Advert advert;

    public Comment() {
    }

    public Comment(String text, Customer customer, Advert advert) {
        this.text = text;
        this.customer = customer;
        this.advert = advert;
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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name="text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="advert_id", nullable = false)
    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
