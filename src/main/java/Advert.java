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
}
