package Media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();
    private int contentLength;
    
    public Book(String title, String category, float cost, ArrayList<String> authors) {
        super(title, category, cost);
        this.authors = authors;
        this.contentLength = title.split(" ").length + category.split(" ").length;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Author '" + authorName + "' has been added to the book.");
        } else {
            System.out.println("Author '" + authorName + "' already exists in the book.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.remove(authorName)) {
            System.out.println("Author '" + authorName + "' has been removed from the book.");
        } else {
            System.out.println("Author '" + authorName + "' not found in the book.");
        }
    }

    public void play() {
        System.out.println("Cannot play books - This is reading material");
    }
    
    public String toString() {
        return "BOOK - " + getTitle() + " - " + getCategory() + " - " + 
               authors + " - " + contentLength + " words: " + getCost() + "$";
    }
}