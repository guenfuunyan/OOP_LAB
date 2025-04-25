package Cart;

import Media.Media;
import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
    
    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(media);
            System.out.println("The media '" + media.getTitle() + "' has been added.");
            if (itemsOrdered.size() == MAX_NUMBERS_ORDERED) {
                System.out.println("The cart is almost full.");
            }
        } else {
            System.out.println("The cart is full. Cannot add more items.");
        }
    }
    
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media '" + media.getTitle() + "' has been removed.");
        } else {
            System.out.println("The media was not found in the cart.");
        }
    }
    
    public double totalCost() {
        double total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }
    
    public void print() {
        System.out.println("****************CART**************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i+1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("**********************************");
    }
    
    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart sorted by title (alphabetically), then by cost (descending).");
    }
    
    public void sortByCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart sorted by cost (descending), then by title (alphabetically).");
    }
    
    public void searchByTitle(String title) {
        boolean found = false;
        System.out.println("Search results for title: \"" + title + "\"");
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found for \"" + title + "\"");
        }
    }
    
    public void searchById(int id) {
        boolean found = false;
        System.out.println("Search results for ID: " + id);
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println(media.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No media found with ID " + id);
        }
    }
    
    public void playMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            media.play();
        } else {
            System.out.println("Media '" + media.getTitle() + "' not found in the cart.");
        }
    }
}