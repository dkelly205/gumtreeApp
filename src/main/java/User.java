import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private List<Advert> adverts;
    private List<Comment> comments;
    private List<Advert> favourites;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.adverts = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.favourites = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

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

    public void addFavourite(Advert advert){
        this.favourites.add(advert);
    }
}
