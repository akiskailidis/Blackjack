public class Card {
    // The figure of the card (e.g., "A", "2", ..., "10", "J", "Q", "K")
    private String figure;

    // Constructor initializes the figure
    public Card(String figure) {
        this.figure = figure;
    }

    // Returns the numeric value of the card
    // Face cards (J, Q, K) are worth 10, Ace is worth 1, numbers are parsed directly
    public int getValue() {
        if (figure.equals("J") || figure.equals("Q") || figure.equals("K")) {
            return 10;
        } else if (figure.equals("A")) {
            return 1;
        } else {
            return Integer.parseInt(figure); // for "2" to "10"
        }
    }

    // Returns true if the card is an Ace
    public boolean isAce() {
        return figure.equals("A");
    }

    // Checks if two cards have the same figure
    @Override
    public boolean equals(Object other) {
        if (other instanceof Card) {
            Card otherCard = (Card) other;
            return this.figure.equals(otherCard.figure);
        }
        return false;
    }

    // Returns the figure as a string
    @Override
    public String toString() {
        return figure;
    }
}
