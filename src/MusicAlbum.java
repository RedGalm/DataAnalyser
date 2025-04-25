import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MusicAlbum extends MusicGeneralInfo {
    private int totalPlays;
    private double avgRating;
    private String artistName;

    public MusicAlbum(String name, int year, String artistName) {
        super(name, year);
        this.artistName = artistName;
        this.totalPlays = 0;
        this.avgRating = 0.0;

    }

    public String getArtistName() {
        return this.artistName;
    }

    public double getAvgRating() {
        return this.avgRating;
    }

    public int getTotalPlays() {
        return this.totalPlays;
    }


    public void readUserData(Map<String, ArrayList<User>> users) {
        AtomicInteger numberOfRatings = new AtomicInteger();
        for (ArrayList<User> arr : users.values()) {
            arr.forEach(value -> {
               if (value.getListenedMusicAlbum().getName().equals(getName())) {
                   numberOfRatings.getAndIncrement();
                   this.avgRating = this.avgRating + value.getRating();
                   this.totalPlays = this.totalPlays + value.getNumberOfPlays();
               }
            });

        }
        this.avgRating = this.avgRating / numberOfRatings.get();
    }

    public String toString() {
        return "Title: " + getName() + "\nYear of release: " + getYear() + "\nBy: " + this.artistName +
                "\nRating: " + this.avgRating + "\nPlays: " + this.totalPlays;
    }
}
