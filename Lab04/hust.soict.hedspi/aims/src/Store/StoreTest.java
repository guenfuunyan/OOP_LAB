package Store;

import Media.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", 
            "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", 
            "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", 
            "Animation", 18.99f);
        System.out.println("--- Testing addDVD ---");
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.displayStore();
        for (int i = 0; i < 100; i++) {
            store.addMedia(new DigitalVideoDisc("Test DVD " + i));
        }
        System.out.println("\n--- Testing removeDVD ---");
        store.removeMedia(dvd2); 
        store.displayStore();

        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Not Existed");
        store.removeMedia(dvd4); 
    }
}