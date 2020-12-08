package BlackJack;

import java.util.ArrayList;
import java.util.TreeSet;

public class Player {

    private String pid;
    private int funds;
    private int bid;
    public ArrayList<Card> hand;
    public ArrayList<Card> splitHand;
    public boolean newAce = false;
    public ArrayList<Card> ace;

    public Player(String pid) {
        this.pid = pid;
        this.funds = 2500;
        this.bid = 0;
        hand = new ArrayList<Card>();
        splitHand = new ArrayList<Card>();
        ace = new ArrayList<>();
    }

    public void add(Card card) {
        hand.add(card);
    }

    public int handValue() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getRank().getRankValue();
        }
        return sum;
    }

    public void containsNewAce(int idx) {
        for (int i = idx; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card.getRank().getRankLabel().equals("ace")) {
                if (handValue() - card.getRank().rankValue <= 10) {
                    hand.set(i, new Card(card.getSuit(), Card.Rank.ACE11));
                } else {
                    hand.set(i, new Card(card.getSuit(), Card.Rank.ACE));
                }
            }
        }
    }

    public String toString() {
        String result = "";
        for (Card card : hand) {
            result += card.toString() + "\n";
        }
        result += handValue();
        return result;
    }

    public void updateFunds(String result) {
        if (result.equalsIgnoreCase("Dealer wins!")) {
            this.funds = getFunds() - getBid();
        } else if (result.equalsIgnoreCase("Player wins!")) {
            this.funds = getFunds() + getBid();
        }
    }

    public void clear() {
        hand.clear();
    }

    public String getId() {
        return pid;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getFunds() {
        return this.funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public int getBid() {
        return this.bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public static void main(String[] args) {
        Deck d = new Deck(); // make a new deck
        Player p1 = new Player("Alejandro");
        d.fillDeck(); // fill the deck
        d.shuffle(); // shuffle the deck
        // Card temp = d.draw();
        p1.add(d.draw());
        p1.add(d.draw());
        p1.add(d.draw());
        System.out.println(p1.toString());
    }

}
