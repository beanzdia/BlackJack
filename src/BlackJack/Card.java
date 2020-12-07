package BlackJack;

import javax.swing.*;
import java.awt.*;

public class Card {

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

    enum Rank {
        TWO(2, "two"), THREE(3, "three"), FOUR(4, "four"), FIVE(5,"five"),
        SIX(6, "six"), SEVEN(7, "seven"), EIGHT (8, "eight"), NINE(9, "nine"),
        TEN(10, "ten"), KING(10, "king"), QUEEN(10, "queen"), JACK(10, "jack"),
        ACE1(1, "ace"), ACE11(11, "ace");

        public int rankValue;
        public final String rankLabel;

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

    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;

    public Card (Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = true;
    }

    public ImageIcon drawCardImage() {
        if (faceUp == false) {
            return new ImageIcon("src/img/down.jpg");
        }
        return new ImageIcon("src/img/" + suit.getSuitLabel() + "_" + rank.getRankLabel() + ".jpg");
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

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

