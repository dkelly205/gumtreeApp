import models.Advert;
import models.Category;
import models.Comment;
import models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentTest {

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
    public void hasText() {
        assertEquals("Hello", comment.getText());
    }

    @Test
    public void hasUser() {
        assertEquals(user, comment.getUser());
    }

    @Test
    public void hasAdvert() {
        assertEquals(advert, comment.getAdvert());
    }
}
