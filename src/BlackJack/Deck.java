package BlackJack;

import java.util.ArrayList;
import java.util.Random;

// This program creates a deck, represented by 52 card- one of each rank and suit.
public class Deck {

    public ArrayList<Card> deck;

    // Creates a deck of cards
    public Deck() {
        deck = new ArrayList<Card>();
    }

    // Fills a deck with cards, one of each rank and suit
    public void fillDeck() {
        for (Card.Suit mySuit : Card.Suit.values()) {
            for (Card.Rank myVal : Card.Rank.values()) {
                if (myVal.rankValue != 11) {
                    deck.add(new Card(mySuit, myVal));
                }
            }
        }
    }

    // Shuffles the deck so that the cards are in random order
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int n = random.nextInt(deck.size());
            Card replace = deck.get(i);
            deck.set(i, deck.get(n));
            deck.set(n, replace);
        }
    }

    // Returns a random card from the deck
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

    // pre: takes in a card
    // post: removes the input card from the deck
    public void remove(Card card) {
        deck.remove(card);
    }

    // pre: takes in a card
    // post: adds the input card to the deck
    public void add(Card card) {
        deck.add(card);
    }

    // Returns a string of the cards (their suits and ranks) in the deck
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
