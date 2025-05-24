package hust.soict.hedspi.aims.cart;

import java.util.Collections;
import java.util.Iterator;
import javax.naming.LimitExceededException;

import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;

	private int qtyOrdered;
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

	public String addMedia(Media media) throws LimitExceededException {
		if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
			throw new LimitExceededException("ERROR: The media's number has reached its limit!");
		}
		if (itemsOrdered.contains(media)) {
			return String.format("%s is already in the cart!", media.getTitle());
		}
		itemsOrdered.add(media);
		qtyOrdered++;
		return String.format("%s has been added!", media.getTitle());
	}

	public void removeMedia(Media media) {
		if (itemsOrdered.isEmpty()) {
			System.out.println("There's nothing to remove!");
			return;
		}
		if (itemsOrdered.remove(media)) {
			qtyOrdered--;
			System.out.println(media.getTitle() + " has been removed from the cart!");
		} else {
			System.out.println("Cannot find media in the cart!");
		}
	}

	public float totalCost() {
		float total = 0.0f;
		for (Media media : itemsOrdered) {
			total += media.getCost();
		}
		return total;
	}

	public void print() {
		System.out.println("***********************CART***********************");
		System.out.println("Ordered Items:");
		for (Media media : itemsOrdered) {
			System.out.println(media);
		}
		System.out.println("Total items: " + qtyOrdered);
		System.out.printf("Total cost: %.2f $\n", totalCost());
		System.out.println("***************************************************");
	}

	public void searchById(int id) {
		for (Media media : itemsOrdered) {
			if (media.getId() == id) {
				System.out.println("Found DVD: " + media);
				return;
			}
		}
		System.out.println("No DVD is found with ID: " + id);
	}

	public void searchByTitle(String title) {
		boolean found = false;
		for (Media media : itemsOrdered) {
			if (media.isMatch(title)) {
				System.out.println("Found DVD: " + media);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No DVD is found with Title: " + title);
		}
	}

	public void sortMediaByTitle() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
		itemsOrdered.forEach(media -> System.out.println(media));
	}

	public void sortMediaByCost() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
		itemsOrdered.forEach(media -> System.out.println(media));
	}

	public Media searchToRemove(String title) {
		for (Media media : itemsOrdered) {
			if (media.getTitle().equals(title)) {
				return media;
			}
		}
		return null;
	}

	public void empty() {
		if (itemsOrdered.isEmpty()) {
			System.out.println("Nothing in cart!");
			return;
		}
		itemsOrdered.clear();
		qtyOrdered = 0;
		System.out.println("Order created successfully!");
		System.out.println("Your cart is empty!\n");
	}

	public ObservableList<Media> getItemsOrdered() {
		return itemsOrdered;
	}
}
