import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    private Dealer dealer;
    private ArrayList<Player> players;
    private ArrayList<Player> pendingPlayers;

    // Constructor initializes dealer and player lists
    public Round(River river) {
        dealer = new Dealer(river);
        players = new ArrayList<>();
        pendingPlayers = new ArrayList<>();
    }

    // Adds a CasinoCustomer to the round as a Player
    public void addPlayer(CasinoCustomer customer) {
        players.add(new Player(customer));
    }

    // Plays a full round of blackjack
    public void playRound() {
        System.out.println("\n---- New Round! ----");

        // Step 1: All players place bets
        for (Player p : players) {
            p.placeBet();
        }

        // Step 2: Dealer deals one face-up card to each player and himself
        for (Player p : players) {
            dealer.deal(p);
        }
        dealer.draw(); // dealer's visible card
        System.out.println(dealer); // show one dealer card

        // Step 3: Deal second card
        for (Player p : players) {
            dealer.deal(p);
        }
        dealer.draw(); // dealer's hidden card

        // Step 4: Show hands
        for (Player p : players) {
            System.out.println(p);
        }

        // Step 5: Check if dealer has blackjack
        if (dealer.getHand().isBlackjack()) {
            System.out.println("Dealer has blackjack!");
            for (Player p : players) {
                if (!p.getHand().isBlackjack()) {
                    p.loses();
                } else {
                    System.out.println("Tie with " + p.getCustomer() + ". Nobody wins");
                }
            }
            return; // round ends
        }

        // Step 6: Check if players have blackjack
        for (Player p : players) {
            if (p.getHand().isBlackjack()) {
                p.winsBlackjack();
            } else {
                playPlayer(p);
            }
        }

        // Step 7: Dealer plays
        dealer.play();
        System.out.println(dealer);

        // Step 8: Settle non-busted players
        for (Player p : pendingPlayers) {
            dealer.settle(p);
        }
    }

    // Plays a normal hand (hit or stand)
    private void playNormalHand(Player p) {
        Scanner scanner = new Scanner(System.in);
        while (!p.getHand().isBust()) {
            System.out.println(p);
            System.out.print("Hit? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                dealer.deal(p);
            } else {
                break;
            }
        }

        if (p.getHand().isBust()) {
            System.out.println("Player " + p.getCustomer() + " busted!");
            p.loses();
        } else {
            pendingPlayers.add(p);
        }
    }

    // Plays a doubled hand (one card only)
    private void playDoubledHand(Player p) {
        p.doubleBet();
        dealer.deal(p);
        System.out.println(p);
        if (p.getHand().isBust()) {
            System.out.println("Player " + p.getCustomer() + " busted after double!");
            p.loses();
        } else {
            pendingPlayers.add(p);
        }
    }

    // Plays a split hand by creating two new players
    private void playSplitHand(Player p) {
        Hand[] splitHands = p.getHand().split();
        Player p1 = new Player(p.getCustomer(), splitHands[0], p.getBet());
        Player p2 = new Player(p.getCustomer(), splitHands[1], p.getBet());

        System.out.println("Split Hand 1:");
        playNormalHand(p1);

        System.out.println("Split Hand 2:");
        playNormalHand(p2);
    }

    // Handles how a player plays: split, double, or normal
    private void playPlayer(Player p) {
        if (p.getHand().canSplit() && p.wantsToSplit()) {
            playSplitHand(p);
        } else if (p.wantsToDouble()) {
            playDoubledHand(p);
        } else {
            playNormalHand(p);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        River river = new River(6);
        Round round = new Round(river);

        CasinoCustomer c1 = new CasinoCustomer("Alice", 100);
        round.addPlayer(c1);

        round.playRound();
    }
}
