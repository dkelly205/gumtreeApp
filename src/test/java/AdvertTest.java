import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdvertTest {

    User user;
    Advert advert;
    Comment comment;

    @Before
    public void setUp() throws Exception {
        user = new User("Danny");
        advert = new Advert("Bike", "BMX", 50.00, Category.SPORTS, "n/a", "01/06/2018", user);
        comment = new Comment("Hello", user, advert);
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
        assertEquals("01/06/2018", advert.getAdmission_date());
    }

    @Test
    public void hasUser(){
        assertEquals(user, advert.getUser());
    }

    @Test
    public void canAddComment(){
        advert.addCommentToAdvert(comment);
        assertEquals(1, advert.getComments().size());
    }

    @Test
    public void canAddFavouriter(){
        advert.addUserToFavouriters(user);
        assertEquals(1, advert.getFavouriters().size());
    }
}

