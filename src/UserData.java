public class UserData {
    private MusicAlbum musicAlbum;
    private int numberOfPlays;
    private double rating;

    public UserData(MusicAlbum musicAlbum, int numberOfPlays, double rating) {
        this.musicAlbum = musicAlbum;
        this.numberOfPlays = numberOfPlays;
        this.rating = rating;
    }

    public int getNumberOfPlays() {
        return this.numberOfPlays;
    }

    public MusicAlbum getMusicAlbum() {
        return this.musicAlbum;
    }

    public double getRating() {
        return this.rating;
    }

    public String toString() {
        return "\nPlayed album: " + this.musicAlbum.getName() + "\nPlays: " + this.numberOfPlays + "\nRate: " + this.rating;
    }

}
