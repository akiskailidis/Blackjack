public class Dealer {
    private River river;
    private Hand hand;

    // Constructor initializes the river and dealer's hand
    public Dealer(River river) {
        this.river = river;
        this.hand = new Hand();
    }

    // Returns the dealer's hand
    public Hand getHand() {
        return hand;
    }

    // Draws one card from the river and adds it to the dealer's hand
    public void draw() {
        Card c = river.nextCard();
        if (c != null) {
            hand.addCard(c);
        }
    }

    // Deals one card from the river to a player
    public void deal(Player p) {
        Card c = river.nextCard();
        if (c != null) {
            p.getHand().addCard(c);
        }
    }

    // Dealer draws cards until score is at least 17
    public void play() {
        while (hand.score() < 17) {
            draw();
        }
    }

    // Resolves the result between dealer and player
    public void settle(Player p) {
        if (p.getHand().isBust()) {
            p.loses();
        } else if (hand.isBust()) {
            p.wins();
        } else {
            int playerScore = p.getHand().score();
            int dealerScore = hand.score();
            if (playerScore > dealerScore) {
                p.wins();
            } else if (playerScore < dealerScore) {
                p.loses();
            } else {
                System.out.println("Tie with " + p.getCustomer() + ". Nobody wins");
            }
        }
    }

    // Returns the string representation of the dealer's hand
    @Override
    public String toString() {
        return "Dealer: " + hand.toString();
    }

    // Main method to test the dealer functionality
    public static void main(String[] args) {
        River river = new River(1);
        Dealer dealer = new Dealer(river);

        // Dealer plays alone
        dealer.play();
        System.out.println(dealer);

        // Optional: test with a player
        CasinoCustomer customer = new CasinoCustomer("TestPlayer", 100);
        Player player = new Player(customer);
        dealer.deal(player);
        dealer.deal(player);

        System.out.println(player);
        dealer.settle(player);
    }
}
