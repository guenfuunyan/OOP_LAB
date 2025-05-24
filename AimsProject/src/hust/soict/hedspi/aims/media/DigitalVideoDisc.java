package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
	
	private String director;
	private int length;
	
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	
	public DigitalVideoDisc(String title) {
		super(title);
	}
	public DigitalVideoDisc(String title, String category, float cost) {
		super(title, category, cost);

	}
	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super(title, category, cost);
		this.director = director;
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title, category, cost);
		this.director = director;
		this.length = length;
	}

	@Override
	public String toString() {
		return this.getId() + "- DVD: " + this.getTitle() + " - Category: " + this.getCategory() + " - Director: " + this.director + " - Length: "
				+ this.length + " - Cost: " + this.getCost() + " $";
	}
	
	public boolean isMatch(String title) {
		return this.getTitle().toLowerCase().contains(title.toLowerCase());
	}
	
	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Playing DVD: " + this.getTitle());
			System.out.println("DVD length: " + this.getLength());
		} else {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
	}
}
