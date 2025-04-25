package Media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, String artist, 
                      String director, float cost) {
        super(title, category, director, cost);
        this.artist = artist;
    }
    
    public String getArtist() {
        return artist;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track " + track.getTitle() + " added");
        } else {
            System.out.println("Track already exists");
        }
    }
    
    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Track removed");
        } else {
            System.out.println("Track not found");
        }
    }
    
    public int getLength() {
        int total = 0;
        for (Track track : tracks) {
            total += track.getLength();
        }
        return total;
    }
    
    public void play() {
        System.out.println("Playing CD: " + getTitle() + " - " + artist);
        System.out.println("Total length: " + getLength());
        for (Track track : tracks) {
            track.play();
        }
    }
    
    public String toString() {
        StringBuilder trackList = new StringBuilder();
        for (Track track : tracks) {
            trackList.append("\n  Track: ").append(track.getTitle()).append(" - ").append(track.getLength()).append("min");
        }
        return "CD - " + getTitle() + " - " + getCategory() + " - " + artist + 
               " - " + getDirector() + " - " + getLength() + "min: " + getCost() + "$" + trackList.toString();
    }
}