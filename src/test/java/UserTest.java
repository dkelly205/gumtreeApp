import models.Advert;
import models.Comment;
import models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;
    Comment comment;
    Advert advert;

    @Before
    public void setUp() throws Exception {
        user = new User("Danny");
        advert = new Advert();
        comment = new Comment("Hello", user, advert);
    }


    @Test
    public void hasName() {
       assertEquals("Danny", user.getName());
    }

    @Test
    public void canAddComment(){
        assertEquals(0, user.getComments().size());
        user.addComment(comment);
        assertEquals(1, user.getComments().size());
    }

    @Test
    public void canAddAdvert() {
        user.addAdvert(advert);
        assertEquals(1, user.getAdverts().size() );
    }

    @Test
    public void canAddFavourite() {
        user.addFavourite(advert);
        assertEquals(1, user.getFavourites().size() );
    }
}
