package Media;

public abstract class Media {
    public static final MediaComparatorByTitleCost COMPARE_BY_TITLE_COST = 
        new MediaComparatorByTitleCost();
    public static final MediaComparatorByCostTitle COMPARE_BY_COST_TITLE = 
        new MediaComparatorByCostTitle();

    private static int nextId = 1;
    private int id;
    private String title;
    private String category;
    private float cost;
    
    public Media(String title) {
        this.title = title;
        this.id = nextId++;
    }
    
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = nextId++;
    }
    
    // Getter methods
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }
    public int getId() { return id; }
    
    // Setter methods
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }
    public void setId(int id) { this.id = id; }
    
    // Abstract methods
    public abstract void play();
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Media media = (Media) obj;
        if (title == null && media.title == null) return true;
        if (title == null || media.title == null) return false;
        return title.equals(media.title);
    }
}