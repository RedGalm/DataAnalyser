import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class TextUI {
    private Scanner reader;
    private Map<String, ArrayList<User>> users;
    private List<MusicAlbum> musicAlbumList;
    private List<Artist> artistList;

    public TextUI(Scanner reader) {
        this.reader = reader;
        this.users = new HashMap<>();
        this.musicAlbumList = new ArrayList<>();
        this.artistList = new ArrayList<>();

        System.out.println("Enter data filename");
        File fileName = new File(reader.nextLine());
        String path = fileName.getAbsolutePath();

        try (Scanner scanner = new Scanner(Paths.get(path))) {
            while (scanner.hasNextLine()) {

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


    public int HashCode(String username) {
        return username.hashCode();
    }


}
