import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextUI textUI = new TextUI(scanner);
        textUI.start();
    }

}