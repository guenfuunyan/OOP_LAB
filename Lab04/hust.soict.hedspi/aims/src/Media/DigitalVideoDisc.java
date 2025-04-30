package Media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    public void play() {
        if (getLength() <= 0) {
            System.out.println("ERROR: DVD " + getTitle() + " cannot be played");
        } else {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength());
        }
    }

    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + 
               " - " + getLength() + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        if (getTitle() == null) return false;
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

    public boolean isMatch(int id) {
        return getId() == id;
    }
}