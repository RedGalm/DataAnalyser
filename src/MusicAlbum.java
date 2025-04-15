import java.util.ArrayList;
import java.util.Map;

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
        int numberOfRatings = 0;
        for (ArrayList<User> arr : users.values()) {
            if (arr.contains(getName())) {
                numberOfRatings++;
                arr.forEach(value -> {
                   this.avgRating = this.avgRating + value.getRating();
                   this.totalPlays = this.totalPlays + value.getNumberOfPlays();
                });
            }
        }
        this.avgRating = this.avgRating / numberOfRatings;
    }

    public String toString() {
        return "Title: " + getName() + "\nYear of release: " + getYear() + "\nBy: " + this.artistName +
                "\nRating: " + this.avgRating + "\nPlays: " + this.totalPlays;
    }
}
