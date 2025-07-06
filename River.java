import java.util.Random;

public class River {
    private static final String[] FIGURES = {
        "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    };

    private Card[] cards;
    private int numberOfCards;
    private int cardsLeft;
    private Random rand;

    // Initializes the river with the given number of decks
    public River(int numberOfDecks) {
        rand = new Random();
        numberOfCards = numberOfDecks * 52;
        cards = new Card[numberOfCards];

        int index = 0;
        for (int d = 0; d < numberOfDecks; d++) {
            for (String figure : FIGURES) {
                for (int i = 0; i < 4; i++) { // 4 cards of each figure per deck
                    cards[index++] = new Card(figure);
                }
            }
        }

        cardsLeft = numberOfCards;
    }

    // Returns a random card and removes it from the available pool
    public Card nextCard() {
        if (cardsLeft == 0) return null;

        int index = rand.nextInt(cardsLeft);
        Card next = cards[index];

        // Swap selected card with the last available one
        cards[index] = cards[cardsLeft - 1];
        cards[cardsLeft - 1] = next;

        cardsLeft--;
        return next;
    }

    // Returns true if remaining cards are less than 25% of the total
    public boolean shouldRestart() {
        return cardsLeft < numberOfCards / 4;
    }

    // Resets the river to full capacity
    public void restart() {
        cardsLeft = numberOfCards;
    }

    // Main method for testing the river functionality
    public static void main(String[] args) {
        River river = new River(1);

        System.out.println("Drawing cards until shouldRestart is true:");
        while (!river.shouldRestart()) {
            System.out.print(river.nextCard() + " ");
        }

        System.out.println("\n\nRestarting river...");
        river.restart();

        System.out.println("Drawing cards until deck is empty:");
        Card card;
        while ((card = river.nextCard()) != null) {
            System.out.print(card + " ");
        }
    }
}
