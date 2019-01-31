import models.Advert;
import models.Category;
import models.Comment;
import models.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CommentTest {

    Customer customer;
    Advert advert;
    Comment comment;
    Date now;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Danny", "password");
        advert = new Advert("Bike", "BMX", 50.00, Category.SPORTS, "n/a", now, customer);
        comment = new Comment("Hello", customer, advert);
    }

    @Test
    public void hasText() {
        assertEquals("Hello", comment.getText());
    }

    @Test
    public void hasUser() {
        assertEquals(customer, comment.getCustomer());
    }

    @Test
    public void hasAdvert() {
        assertEquals(advert, comment.getAdvert());
    }
}
