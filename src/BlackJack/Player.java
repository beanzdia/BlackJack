package BlackJack;

import java.util.ArrayList;

public class Player {

    private String pid;
    private int funds;
    private int bid;
    public ArrayList<Card> hand;
    public ArrayList<Card> splitHand;

    public Player(String pid) {
        this.pid = pid;
        this.funds = 2500;
        this.bid = 0;
        hand = new ArrayList<Card>();
        splitHand = new ArrayList<Card>();
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

    public Card containsNewAce() {
        for (Card card : hand) {
            if (card.getRank().getRankLabel().equals("ace") && card.getRank().getRankValue() == 0) {
                return card;
            }
        }
        return null;
    }

    public int indexOfNewAce() {
        for (int i = 0; i < hand.size(); i++) {
            Card temp = hand.get(i);
            if (temp.getRank().getRankValue() == 0 && temp.getRank().getRankLabel().equals("ace")) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        String result = "";
        for (Card card : hand) {
            result += card.toString() + "\n";
        }
        result += handValue();
        return result;
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
