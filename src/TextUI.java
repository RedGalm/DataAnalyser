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
                    System.out.println("Error: Unspecified data at the line " + lineNum);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void start() {
        String command = "";
        while (!(command.equalsIgnoreCase("exit"))) {
            System.out.println("Choose data set:" +
                    "\n1 - Users" +
                    "\n2 - MusicAlbums" +
                    "\n3 - Artists" +
                    "\nexit - closes program");
            command = reader.nextLine();
            if (command.equalsIgnoreCase("Users")) {
                System.out.println("Commands list:" +
                        "\nuserInfo - search for specified user" +
                        "\nprintAll - prints all data" +
                        "\nexit - closes program");
                command = reader.nextLine();
                if (command.equalsIgnoreCase("userInfo")) {
                    System.out.println("Enter user name");
                    String user = reader.nextLine();
                    this.users.get(user).forEach(System.out::println);
                }

                if (command.equalsIgnoreCase("printAll")) {
                    for (String key: this.users.keySet()) {
                        this.users.get(key).forEach(System.out::println);
                    }
                }
            }

            if (command.equalsIgnoreCase("MusicAlbums") || command.equalsIgnoreCase("Artists")) {
                List<?> selectedList = new ArrayList<>();
                if (command.equalsIgnoreCase("MusicAlbums")) {
                    selectedList = this.musicAlbumList;
                } else {
                    selectedList = this.artistList;
                }

                System.out.println("Commands list:" +
                        "\nexit - closes program" +
                        "\nsort - sorts data by order" +
                        "\nsearch - search by specified word" +
                        "\nprintAll - prints all data");

                command = reader.nextLine();

                switch (command) {
                    case "exit":
                        break;
                    case "sort":

                }

            }



        }
    }

    public int hashCode(String username) {
        return username.hashCode();
    }





}
