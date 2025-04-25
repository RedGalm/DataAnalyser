import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Artist extends MusicGeneralInfo {
    private int totalPlays;
    private double avgRating;

    public Artist(String name, int year) {
        super(name, year);
        this.totalPlays = 0;
        this.avgRating = 0.0;
    }

    public void readUserData(Map<String, ArrayList<User>> users) {
        AtomicInteger numberOfRatings = new AtomicInteger();
        for (ArrayList<User> arr : users.values()) {
            arr.forEach(value -> {
               if (value.getListenedMusicAlbum().getArtistName().equals(getName())) {
                   numberOfRatings.getAndIncrement();
                   this.avgRating = this.avgRating + value.getListenedMusicAlbum().getAvgRating();
                   this.totalPlays = this.totalPlays + value.getListenedMusicAlbum().getTotalPlays();
               }
            });
        }

        this.avgRating = this.avgRating / numberOfRatings.get();
    }

    public String toString() {
        return "Artist: " + getName() + "\nFormed: " + getYear() + "\nRating: " + this.avgRating +
                "\nPlays: " + this.totalPlays;
    }

}
