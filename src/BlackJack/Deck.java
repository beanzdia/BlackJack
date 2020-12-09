package BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    public ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public void fillDeck() {
        for (Card.Suit mySuit : Card.Suit.values()) {
            for (Card.Rank myVal : Card.Rank.values()) {
                if (myVal.rankValue != 11) {
                    deck.add(new Card(mySuit, myVal));
                }
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int n = random.nextInt(deck.size());
            Card replace = deck.get(i);
            deck.set(i, deck.get(n));
            deck.set(n, replace);
        }
    }

    public Card draw() {
        if (deck.isEmpty()) {
            fillDeck();
            shuffle();
        }
        Random random = new Random();
        int n = random.nextInt(deck.size());
        Card drawCard = deck.get(n);
        // Need the remove because if randomly the same card is selected in the same hand then they have the same attributes
        // problem with aces and dealre facedown
        deck.remove(n);
        drawCard.setFaceUp(true);
        return drawCard;
    }

    public void remove(Card card) {
        deck.remove(card);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public String toString() {
        String result = "";
        for (Card card : deck) {
            result += card.toString() + "\n";
        }
        return result;
    }


    public static void main(String[] args) {
        Deck d = new Deck();
        d.fillDeck();
        d.shuffle();
        Card temp = d.draw();
        Card c = d.draw();
        System.out.println(d.toString());
    }
}
