import models.Advert;
import models.Category;
import models.Comment;
import models.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AdvertTest {

    Customer customer;
    Advert advert;
    Comment comment;
    Date now;

    @Before
    public void setUp() throws Exception {
        now = new Date();
        customer = new Customer("Danny", "password");
        advert = new Advert("Bike", "BMX", 50.00, Category.SPORTS, "n/a", now, customer);
        comment = new Comment("Hello", customer, advert);
    }

    @Test
    public void hasTitle() {
        assertEquals("Bike", advert.getTitle());
    }

    @Test
    public void hasDescription() {
        assertEquals("BMX", advert.getDescription());
    }

    @Test
    public void hasPrice() {
        assertEquals(50.00, advert.getPrice(), 0.01);
    }

    @Test
    public void hasCategory() {
        assertEquals(Category.SPORTS, advert.getCategory());
    }

    @Test
    public void hasImage(){
        assertEquals("n/a", advert.getImage());
    }

    @Test
    public void hasAdmissionDate() {
        assertEquals(now, advert.getAdmission_date());
    }

    @Test
    public void hasUser(){
        assertEquals(customer, advert.getCustomer());
    }

    @Test
    public void canAddComment(){
        advert.addCommentToAdvert(comment);
        assertEquals(1, advert.getComments().size());
    }

    @Test
    public void canAddFavouriter(){
        advert.addCustomerToFavouriters(customer);
        assertEquals(1, advert.getFavouriters().size());
    }
}

