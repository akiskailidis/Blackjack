import java.util.Scanner;

public class BlackjackTable {
    private River river;
    private CasinoCustomer[] customers;
    private int numPlayers;

    // Constructor asks for player info and sets up table
    public BlackjackTable(int numPlayers) {
        this.numPlayers = numPlayers;
        customers = new CasinoCustomer[numPlayers];
        river = new River(6);

        for (int i = 0; i < numPlayers; i++) {
            customers[i] = createCasinoCustomer(i + 1);
        }
    }

    // Prompts user to create a casino customer
    private CasinoCustomer createCasinoCustomer(int playerNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give customer name and available money for player " + playerNumber + ": ");
        String name = scanner.next();
        double money = scanner.nextDouble();
        return new CasinoCustomer(name, money);
    }

    // Main game loop
    public void play() {
        while (true) {
            // Filter active players
            boolean hasActivePlayers = false;
            Round round = new Round(river);

            for (CasinoCustomer c : customers) {
                if (!c.isBroke()) {
                    hasActivePlayers = true;
                    round.addPlayer(c);
                }
            }

            if (!hasActivePlayers) {
                System.out.println("All players are broke. Game over.");
                break;
            }

            if (river.shouldRestart()) {
                System.out.println("Shuffling new decks...");
                river.restart();
            }

            round.playRound();
        }
    }
}
