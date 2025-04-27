import java.nio.file.Paths;
import java.util.*;

public class TextUI {
    private Scanner reader;
    private Map<String, ArrayList<User>> users;
    private List<MusicGeneralInfo> musicAlbumList;
    private List<MusicGeneralInfo> artistList;
    private List<MusicGeneralInfo> selectedList;

    public TextUI(Scanner reader) {
        this.reader = reader;
        this.users = new HashMap<>();
        this.musicAlbumList = new ArrayList<>();
        this.artistList = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get("/repository/DataAnalyser/src/data.txt"))) {
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                int existsOrNot = 0;
                String row = scanner.nextLine();
                lineNum++;
                String[] arr = row.split(",");

                if (arr[0].equalsIgnoreCase("userAndAlbumData")) {
                    users.putIfAbsent(arr[1], new ArrayList<>());
                    MusicAlbum album = new MusicAlbum(arr[2], Integer.parseInt(arr[3]), arr[4]);

                    for (MusicGeneralInfo value: this.musicAlbumList) {
                        if (value.getName().equals(album.getName())) {
                            existsOrNot = 1;
                        }
                    }

                    if (existsOrNot == 0) {
                        this.musicAlbumList.add(album);
                    }
                    users.get(arr[1]).add(new User(album, Integer.parseInt(arr[5]), Double.parseDouble(arr[6])));


                } else if (arr[0].equalsIgnoreCase("artistData")) {
                    Artist artist = new Artist(arr[1], Integer.parseInt(arr[2]));
                    for (MusicGeneralInfo value: this.artistList) {
                        if (value.getName().equals(artist.getName())) {
                            existsOrNot = 1;
                        }
                    }

                    if (existsOrNot == 0) {
                        this.artistList.add(artist);
                    }

                } else {
                    System.out.println("Error: Unspecified data at the line " + lineNum);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }

        this.musicAlbumList.forEach(value -> value.readUserData(this.users));
        this.artistList.forEach(value -> value.readUserData(this.users));

    }

    public void start() {
        String command;
        while (true) {
            System.out.println("\nChoose data set:" +
                    "\n1 - Users" +
                    "\n2 - Music albums" +
                    "\n3 - Artists" +
                    "\nexit - closes program");
            command = reader.nextLine();

            if (command.equalsIgnoreCase("1")) {
                System.out.println("Commands list:" +
                        "\nuserInfo - search for specified user" +
                        "\nprintAll - prints all data" +
                        "\nback - return to data set choice");
                command = reader.nextLine();

                switch (command.toLowerCase()) {
                    case "userinfo":
                        System.out.println("Enter user name");
                        String user = reader.nextLine();
                        if (this.users.containsKey(user)) {
                            this.users.get(user).forEach(System.out::println);
                        } else {
                            System.out.println("Error: Unknown user");
                        }
                        break;

                    case "printall":
                        for (String key : this.users.keySet()) {
                            System.out.print("\n" + key);
                            this.users.get(key).forEach(System.out::println);
                        }
                        break;

                    case "back":
                        break;

                    default:
                        System.out.println("Error: Unknown command");
                        break;
                    }
                } else if (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("3")) {

                if (command.equalsIgnoreCase("2")) {
                    this.selectedList = this.musicAlbumList;
                } else {
                    this.selectedList = this.artistList;
                }

                while (!(command.equalsIgnoreCase("back"))) {
                System.out.println("\nCommands list:" +
                        "\nback - return to data set choice" +
                        "\nsorted - prints data in order" +
                        "\nsearch - search data set by text" +
                        "\nprintAll - prints all data");
                command = reader.nextLine();

                switch (command.toLowerCase()) {
                    case "back":
                        break;

                    case "sorted":
                        System.out.println("Sort by:" +
                                "\nName" +
                                "\nYear");
                        command = reader.nextLine();

                        if (command.equalsIgnoreCase("Name")) {
                            Comparator<MusicGeneralInfo> comparator = Comparator.comparing(MusicGeneralInfo::getName);
                            List<MusicGeneralInfo> printList = this.selectedList;
                            printList.sort(comparator);
                            printList.forEach(value -> System.out.println("\n" + value));
                        }

                        if (command.equalsIgnoreCase("Year")) {
                            this.selectedList.stream()
                                    .sorted((p1, p2) -> {
                                return p1.getYear() - p2.getYear();
                            }).forEach(value -> System.out.println("\n" + value));
                        }
                        break;

                    case "search":
                        System.out.println("Enter text");
                        String text = reader.nextLine();
                        this.selectedList.stream()
                                .filter(value -> String.valueOf(value).contains(text))
                                .forEach(value -> System.out.println("\n" + value));
                        break;

                    case "printall":
                        this.selectedList.forEach(value -> System.out.println("\n" + value));
                        break;

                    default:
                        System.out.println("Error: Unknown command");
                        break;
                }
                }
            } else if (command.equalsIgnoreCase("exit")) {
                break;

            } else {
                System.out.println("Error: Unknown command");
            }
        }
    }
}
