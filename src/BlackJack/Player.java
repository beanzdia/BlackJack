package BlackJack;

import java.util.ArrayList;

// This program creates a player, which is mainly represeneted by a hand of cards, an amount of funds, and a bid amount.
public class Player {
    private String pid;
    private int funds;
    private int splitFunds;
    private int bid;
    private int splitBid;
    public ArrayList<Card> hand;
    public ArrayList<Card> splitHand;
    public boolean newAce = false;
    public boolean split = false;

    // pre: takes in a player's name/identifier
    // post: creates the player name, sets the funds to 2500 and creates a new hand
    public Player(String pid) {
        this.pid = pid;
        this.funds = 2500;
        this.bid = 0;
        hand = new ArrayList<Card>();
        splitHand = new ArrayList<Card>();
    }

    // Adds a card to the hand
    public void add(Card card) {
        hand.add(card);
    }

    // Adds a card to the split hand
    public void splitAdd(Card card) {
        splitHand.add(card);
    }

    // Returns the total value of the cards in hand
    public int handValue() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getRank().getRankValue();
        }
        return sum;
    }

    // Returns the total value of the cards in a split hand
    public int splitHandValue() {
        int sum = 0;
        for (Card card : splitHand) {
            sum += card.getRank().getRankValue();
        }
        return sum;
    }

    // pre: takes in an index of the card draw
    // post: sets the aces in hand to be the values the user or program has previously determines
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

    // Returns a string of the cards (suits and ranks) in hand
    public String toString() {
        String result = "";
        for (Card card : hand) {
            result += card.toString() + "\n";
        }
        result += handValue();
        return result;
    }

    // pre: takes in a result, indicating the winner of the game
    // post: updates the funds depending on the results:
    //      - if dealer wins, funds decrement by bid amount
    //      - if player wins, funds increment by bid amount
    public void updateFunds(String result) {
        int bidVal = getBid();
        if (result.equalsIgnoreCase("Dealer wins!")) {
            this.funds = getFunds() - bidVal;
        } else if (result.equalsIgnoreCase("Player wins!")) {
            this.funds = getFunds() + bidVal;
        }
        this.bid = 0;
    }

    // pre: takes in a result, indicating the winner of the game with a split hand
    // post: updates the funds depending on the results:
    //      - if dealer wins, funds decrement by split bid amount
    //      - if player wins, funds increment by split bid amount
    public void updateSplitFunds(String result) {
        int bidVal = getSplitBid();
        if (result.equalsIgnoreCase("Dealer wins!")) {
            this.funds = getFunds() - bidVal;
        } else if (result.equalsIgnoreCase("Player wins!")) {
            this.funds = getFunds() + bidVal;
        }
        this.splitBid = 0;
    }

    // Removes the cards from all hands
    public void clear() {
        hand.clear();
        splitHand.clear();
    }

    // Returns the name of the player
    public String getId() {
        return pid;
    }

    // Returns the hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Returns the split hand
    public ArrayList<Card> getSplitHand() {
        return this.splitHand;
    }

    // Returns the funds
    public int getFunds() {
        return this.funds;
    }

    // Returns the split funds
    public int getSplitFunds() {
        return this.splitFunds;
    }

    // pre: takes in a funds amount
    // post: creates funds equal to the input funds
    public void setFunds(int funds) {
        this.funds = funds;
    }

    // pre: takes in a a funds amount for a split game
    // post: creates funds equal to the input funds
    public void setSplitFunds (int funds) {
        this.splitFunds = funds;
    }

    // Returns the bid amount
    public int getBid() {
        return this.bid;
    }

    // Returns the split bid amount
    public int getSplitBid() {
        return this.splitBid;
    }

    // pre: takes in a bid amount
    // post: creates a bid equal to the input bid amount
    public void setBid(int bid) {
        this.bid = bid;
    }

    // pre: takes in a split bid amount
    // post: creates a bid equal to the input bid amount
    public void setSplitBid(int bid) {
        this.splitBid = bid;
    }
}
