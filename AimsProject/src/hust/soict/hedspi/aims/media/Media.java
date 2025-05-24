package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media implements Comparable<Media> {

	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

	private static int nbMedia = 0;

	private final int id;
	private String title;
	private String category;
	private float cost;

	public Media(String title) {
		this(title, "", 0f);
	}

	public Media(String title, String category) {
		this(title, category, 0f);
	}

	public Media(String title, String category, float cost) {
		this.id = ++nbMedia;
		this.title = title;
		this.category = category;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public float getCost() {
		return cost;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isMatch(String title) {
		return this.title != null && this.title.toLowerCase().contains(title.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Media)) return false;
		Media other = (Media) obj;
		return this.title != null && this.title.equals(other.title);
	}

	@Override
	public String toString() {
		return String.format("Media: %s - Category: %s - Cost: %.2f$", title, category, cost);
	}

	@Override
	public int compareTo(Media other) {
		int titleComparison = this.title.compareTo(other.title);
		return (titleComparison != 0) ? titleComparison : Float.compare(this.cost, other.cost);
	}
}
