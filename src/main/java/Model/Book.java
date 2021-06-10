package Model;

import java.io.Serializable;
import java.util.Arrays;

public class Book implements Serializable {
    private int id;
    private String name;
    private double price;
    private String description;
    private String Author;
    private String[] Category;
    private String Publisher;
    private String Image;
    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", Author='" + Author + '\'' +
                ", Category=" + Arrays.toString(Category) +
                ", Publisher='" + Publisher + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public Book(int id, String name, double price, String description, String author, String[] category, String publisher, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        Author = author;
        Category = category;
        Publisher = publisher;
        Image = image;
    }

    public Book(int id, String name, double price, String description, String author, String[] category, String publisher) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        Author = author;
        Category = category;
        Publisher = publisher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setCategory(String[] category) {
        Category = category;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return Author;
    }

    public String[] getCategory() {
        return Category;
    }

    public String getPublisher() {
        return Publisher;
    }
}
