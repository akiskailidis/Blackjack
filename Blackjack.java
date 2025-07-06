import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give the number of players: ");
        int numPlayers = scanner.nextInt();

        BlackjackTable table = new BlackjackTable(numPlayers);
        table.play();
    }
}
