package models;

public enum Category {
    HOUSEHOLD("household"),
    ELECTRONIC("electronic"),
    SPORTS("sports"),
    TOYS("toys"),
    PETS("pets"),
    OTHER("other");


    private String description;


    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


