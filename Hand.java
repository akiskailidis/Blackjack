import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    // Constructor initializes the hand
    public Hand() {
        cards = new ArrayList<>();
    }

    // Adds a card to the hand
    public void addCard(Card c) {
        cards.add(c);
    }

    // Calculates the hand's score, treating Aces as 11 if it helps
    public int score() {
        int sum = 0;
        int aceCount = 0;

        for (Card c : cards) {
            sum += c.getValue();
            if (c.isAce()) {
                aceCount++;
            }
        }

        if (aceCount > 0 && sum + 10 <= 21) {
            return sum + 10;
        }
        return sum;
    }

    // Returns true if the hand has two identical cards (can be split)
    public boolean canSplit() {
        return cards.size() == 2 && cards.get(0).equals(cards.get(1));
    }

    // Splits the hand into two separate hands
    public Hand[] split() {
        Hand[] splitHands = new Hand[2];
        splitHands[0] = new Hand();
        splitHands[1] = new Hand();
        splitHands[0].addCard(cards.get(0));
        splitHands[1].addCard(cards.get(1));
        return splitHands;
    }

    // Checks if the hand is a blackjack (two cards summing to 21)
    public boolean isBlackjack() {
        return cards.size() == 2 && score() == 21;
    }

    // Returns true if the hand has busted (score > 21)
    public boolean isBust() {
        return score() > 21;
    }

    // Returns a string representation of the hand, including score
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card c : cards) {
            sb.append(c.toString()).append(" ");
        }

        // Show dual value if Ace can count as both 1 and 11
        int rawScore = 0;
        boolean hasAce = false;
        for (Card c : cards) {
            rawScore += c.getValue();
            if (c.isAce()) {
                hasAce = true;
            }
        }

        if (hasAce && rawScore + 10 <= 21) {
            sb.append("(Score: ").append(rawScore).append("/").append(rawScore + 10).append(")");
        } else {
            sb.append("(Score: ").append(score()).append(")");
        }

        return sb.toString();
    }

    // Main method to demonstrate hand functionality
    public static void main(String[] args) {
        Hand hand = new Hand();
        hand.addCard(new Card("A"));
        hand.addCard(new Card("A"));

        System.out.println("Initial hand: " + hand);
        System.out.println("Can split? " + hand.canSplit());

        Hand[] splitHands = hand.split();
        System.out.println("After split:");
        System.out.println("Hand 1: " + splitHands[0]);
        System.out.println("Hand 2: " + splitHands[1]);

        // Add cards to first split hand
        splitHands[0].addCard(new Card("K"));
        System.out.println("Hand 1 after adding K: " + splitHands[0]);
        System.out.println("Is Blackjack? " + splitHands[0].isBlackjack());

        splitHands[0].addCard(new Card("A"));
        System.out.println("Hand 1 after adding A: " + splitHands[0]);

        splitHands[0].addCard(new Card("10"));
        System.out.println("Hand 1 after adding 10: " + splitHands[0]);
        System.out.println("Is Bust? " + splitHands[0].isBust());
    }
}
