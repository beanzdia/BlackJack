package BlackJack;

import javax.swing.*;
import java.awt.*;

// This program creates a card, which is represented by a suit and a rank
public class Card {

    // Defines the 4 suits: spades, diamonds, clubs, and hearts
    enum Suit {
        SPADES("spade"), DIAMONDS("diamond"), CLUBS("club"), HEARTS("heart");
        private final String suitLabel;

        private Suit(String suitLabel) {
            this.suitLabel = suitLabel;
        }

        public String getSuitLabel() {
            return suitLabel;
        }
    }

    // Defines the 14 ranks, represented by a value (1-11) and a name (ace-king)
    enum Rank {
        TWO(2, "two"), THREE(3, "three"), FOUR(4, "four"), FIVE(5,"five"),
        SIX(6, "six"), SEVEN(7, "seven"), EIGHT (8, "eight"), NINE(9, "nine"),
        TEN(10, "ten"), KING(10, "king"), QUEEN(10, "queen"), JACK(10, "jack"),
        ACE(1, "ace"), ACE11(11,"ace");

        public int rankValue;
        public String rankLabel;

        Rank(int rankValue, String rankLabel) {
            this.rankValue = rankValue;
            this.rankLabel = rankLabel;
        }

        public int getRankValue() {
            return rankValue;
        }

        public String getRankLabel() {
            return rankLabel;
        }

        public void setRankValue(int n) {
            this.rankValue = n;
        }
    }

    private Suit suit;
    private Rank rank;
    private boolean faceUp;

    // pre: takes in a suit and rank
    // Creates an instance of card, represented by a suit, rank, and direction of card (face up or face down)
    public Card (Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = true;
    }

    // Gets the image of the card, either face up or face down
    public ImageIcon drawCardImage() {
        if (faceUp == false) {
            return new ImageIcon("src/img/down.jpg");
        }
        return new ImageIcon("src/img/" + suit.getSuitLabel() + "_" + rank.getRankLabel() + ".jpg");
    }

    // pre: takes in true or false, where true represents faceup
    // post: defines placement of a card as face up or down
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    // Returns the suit of a card
    public Suit getSuit() {
        return this.suit;
    }

    // Returns the rank (value and name) of a card
    public Rank getRank() {
        return this.rank;
    }

    // Sets the rank of a card
    public void setRank(Rank rank) {this.rank = rank;}

    // Returns a string listing the suit and value
    public String toString() {
        return "Suit: " + suit + " Value: " + rank;
    }

    public static void main(String[] args) {
        Card c = new Card(Suit.SPADES, Rank.KING);
        JLabel label = new JLabel();
        label.setText("Hello");
        label.setIcon(c.drawCardImage());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.add(label);
    }
}

