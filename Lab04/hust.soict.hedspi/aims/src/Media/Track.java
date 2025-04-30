package Media;

public class Track implements Playable {
    private String title;
    private int length;
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }
    public void play() {
        if (length <= 0) {
            System.out.println("ERROR: Track " + title + " cannot be played");
        } else {
            System.out.println("Playing track: " + title + ". Length: " + length);
        }
    }
    public String getTitle() { return title; }
    public int getLength() { return length; }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        if (title == null && track.title == null) return length == track.length;
        if (title == null || track.title == null) return false;
        return title.equals(track.title) && length == track.length;
    }
}