package hust.soict.hedspi.aims.store;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;

public class Store {
	
	private ArrayList<Media> itemsInStore = new ArrayList<Media>();
	
	public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}

	private boolean checkMedia(Media media) {
		for (Media mediae : itemsInStore) {
			if (mediae.equals(media)) {
				return true;
			}
		}
		return false;
	}
	
	public void removeMedia(Media media) {
		if (checkMedia(media)) {
			itemsInStore.remove(media);
			System.out.println("The \" " + media.getTitle() + " \" 've been deleted from the store.");
		} else {
			System.out.println("There's no " + media.getTitle() + " in the store.");
		}
	}
	
	public void addMedia(Media media) {
		if (!checkMedia(media)) {
			itemsInStore.add(media);
			System.out.println("The \" " + media.getTitle() + " \" 've been added into the store.");
		} else {
			System.out.println("The \" " + media.getTitle() + " \" 've already been in the store.");
		}
	}
	
	public void print() {
		if (itemsInStore.size() == 0) {
			System.out.println("The store is empty!");
		} else {
			System.out.println("************************INVENTORY***********************");
			for (Media media : itemsInStore) {
				System.out.println(media);
			}
			System.out.println("********************************************************");
		}
	}
	
	public Media search(String title) {
		for (Media media : itemsInStore) {
			if (media.getTitle().equals(title)) {
				return media;
			}
		}
		return null;
	}
}
