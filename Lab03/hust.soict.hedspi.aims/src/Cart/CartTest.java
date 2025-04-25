package Cart;

import Media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
            "Animation", "Roger Allers", 87, 19.95f);
        cart.addMedia(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
            "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addMedia(dvd2);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
            "Animation", 18.99f);
        cart.addMedia(dvd3);
        System.out.println("\n--- Testing print cart ---");
        cart.print();
        System.out.println("\n--- Testing search by ID ---");
        cart.searchById(1);
        cart.searchById(99); 
        System.out.println("\n--- Testing search by title ---");
        cart.searchByTitle("lion"); 
        cart.searchByTitle("Star");   
        cart.searchByTitle("Aladin"); 
        cart.searchByTitle("Harry"); 
    }
}