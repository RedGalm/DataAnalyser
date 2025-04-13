import java.util.ArrayList;
import java.util.Map;

public class MusicAlbum extends MusicGeneralInfo {
    private int totalPlays;
    private double avgRating;
    private String artistName;

    public MusicAlbum(String name, int year, String artistName) {
        super(name, year);
        this.totalPlays = 0;
        this.avgRating = 0.0;
        this.artistName = artistName;
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


    public void readUserData(Map<String, ArrayList<UserData>> users) {
        int numberOfRatings = 0;
        for (ArrayList<UserData> arr : users.values()) {
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
}
