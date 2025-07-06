import java.util.Scanner;

public class Player {
    private CasinoCustomer customer;
    private Hand hand;
    private double bet;

    // Constructor with only the customer
    public Player(CasinoCustomer customer) {
        this.customer = customer;
        this.hand = new Hand();
        this.bet = 0;
    }

    // Full constructor
    public Player(CasinoCustomer customer, Hand hand, double bet) {
        this.customer = customer;
        this.hand = hand;
        this.bet = bet;
    }

    // Accessor methods
    public CasinoCustomer getCustomer() {
        return customer;
    }

    public Hand getHand() {
        return hand;
    }

    public double getBet() {
        return bet;
    }

    // Called when player wins normally
    public void wins() {
        System.out.println("Player " + customer + " won! Collects " + bet + " euros.");
        customer.collectBet(bet);
    }

    // Called when player wins with blackjack
    public void winsBlackjack() {
        double payout = 1.5 * bet;
        System.out.println("Blackjack! Player " + customer + " collects " + payout + " euros.");
        customer.collectBet(payout);
    }

    // Called when player loses
    public void loses() {
        System.out.println("Player " + customer + " lost! Pays " + bet + " euros.");
        customer.payBet(bet);
    }

    // Prompts the player to place a valid bet
    public void placeBet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(customer + " has " + customer.getMoney() + " euros left");

        while (true) {
            System.out.print(customer + ", place your bet: ");
            bet = scanner.nextDouble();
            if (bet >= 1 && customer.canCover(bet)) {
                break;
            }
            System.out.println("Invalid bet. Must be at least 1 euro and within your balance.");
        }
    }

    // Doubles the current bet
    public void doubleBet() {
        bet *= 2;
    }

    // Asks the player if they want to double the bet
    public boolean wantsToDouble() {
        if (!customer.canCover(bet * 2)) return false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to double? (y/n): ");
        String input = scanner.next().trim().toLowerCase();
        return input.equals("y");
    }

    // Asks the player if they want to split the hand
    public boolean wantsToSplit() {
        if (!customer.canCover(bet * 2)) return false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to split? (y/n): ");
        String input = scanner.next().trim().toLowerCase();
        return input.equals("y");
    }

    // Returns player name and hand
    @Override
    public String toString() {
        return customer + ": " + hand.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        CasinoCustomer c = new CasinoCustomer("Alice", 50);
        Player p = new Player(c);

        p.placeBet();
        System.out.println("Wants to split? " + p.wantsToSplit());
        System.out.println("Wants to double? " + p.wantsToDouble());

        p.wins();
        c.printState();

        p.winsBlackjack();
        c.printState();

        p.loses();
        c.printState();
    }
}
