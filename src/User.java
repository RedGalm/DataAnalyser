public class User {
    private MusicAlbum listenedMusicAlbum;
    private int numberOfPlays;
    private double rating;

    public User(MusicAlbum musicAlbum, int numberOfPlays, double rating) {
        this.listenedMusicAlbum = musicAlbum;
        this.numberOfPlays = numberOfPlays;
        if (rating <= 5.0) {
            this.rating = rating;
        }
    }

    public int getNumberOfPlays() {
        return this.numberOfPlays;
    }

    public MusicAlbum getListenedMusicAlbum() {
        return this.listenedMusicAlbum;
    }

    public double getRating() {
        return this.rating;
    }

    public String toString() {
        return "\nPlayed album: " + this.listenedMusicAlbum.getName() + "\nPlays: " + this.numberOfPlays + "\nRate: " + this.rating;
    }

}
