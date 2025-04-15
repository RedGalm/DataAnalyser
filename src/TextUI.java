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
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                lineNum++;
                String[] arr = row.split(",");
                if (arr[0].equalsIgnoreCase("userAndAlbumData")) {
                    hashCode(arr[1]);
                    users.putIfAbsent(arr[1], new ArrayList<>());
                    MusicAlbum album = new MusicAlbum(arr[2], Integer.parseInt(arr[3]), arr[4]);
                    if (!(musicAlbumList.contains(album))) {
                        musicAlbumList.add(album);
                    }
                    users.get(arr[1]).add(new User(album, Integer.parseInt(arr[4]), Double.parseDouble(arr[5])));
                } else if (arr[0].equalsIgnoreCase("artistData")) {
                    Artist artist = new Artist(arr[1], Integer.parseInt(arr[2]));
                    if (!(this.artistList.contains(artist))) {
                        this.artistList.add(artist);
                    }
                } else {
                    System.out.println("Error: Unspecified data at line " + lineNum);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void start() {
        while (true) {
            System.out.println("Commands list:" +
                    "\nexit - closes program" +
                    "\nsort - sorts data by order" +
                    "\n");
        }
    }


    public int hashCode(String username) {
        return username.hashCode();
    }


}
