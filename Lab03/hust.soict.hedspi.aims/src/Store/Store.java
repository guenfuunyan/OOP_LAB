package Store;

import Media.Media;
import java.util.ArrayList;

public class Store {
    public static final int MAX_STORE_ITEMS = 100;
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (itemsInStore.size() < MAX_STORE_ITEMS) {
            itemsInStore.add(media);
            System.out.println("Media '" + media.getTitle() + "' has been added to the store.");
        } else {
            System.out.println("Store is full. Cannot add more media.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Media '" + media.getTitle() + "' has been removed from the store.");
        } else {
            System.out.println("Media '" + media.getTitle() + "' not found in the store.");
        }
    }

    public void displayStore() {
        System.out.println("\n**********STORE**********");
        System.out.println("Available Media:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i+1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("*************************");
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return media;
            }
        }
        return null;
    }
}