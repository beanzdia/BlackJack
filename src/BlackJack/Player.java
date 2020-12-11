// The Player class represents a player in BlackJack that has a hand of cards, funds, and bids.
// A Player can add cards to their hand, find a new ace in their hand, and update their funds
// and bids.

package BlackJack;

import java.util.ArrayList;

public class Player {
    private String pid;
    private int funds;
    private int bid;
    private int splitBid;
    // Stores all of the cards in the player's hand
    public ArrayList<Card> hand;
    // Stores all of the cards in the player's splitHand
    public ArrayList<Card> splitHand;
    // Determines whether a new ace is present in a player's hand
    public boolean newAce = false;
    // Determines whether a new split ace is present in a player's hand
    public boolean newSplitAce = false;
    // Determine whether the player was asked to split their hand
    public boolean split = false;

    // post: Constructs a player with an ID, set funds and bid, and empty hands.
    //       The parameter pid is the name of the player.
    public Player(String pid) {
        this.pid = pid;
        this.funds = 2500;
        this.bid = 0;
        hand = new ArrayList<Card>();
        splitHand = new ArrayList<Card>();
    }

    // post: Adds a passed in card parameter to the player's hand. The card parameter
    //       represents a card with a suit and rank.
    public void add(Card card) {
        hand.add(card);
    }

    // post: Adds a passed in card parameter to the player's splitHand. The card parameter
    //       represents a card with a suit and rank.
    public void splitAdd(Card card) {
        splitHand.add(card);
    }

    // post: Returns the total rank value of the cards in hand.
    public int handValue() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getRank().getRankValue();
        }
        return sum;
    }

    // post: Returns the total rank value of the cards in the split hand.
    public int splitHandValue() {
        int sum = 0;
        for (Card card : splitHand) {
            sum += card.getRank().getRankValue();
        }
        return sum;
    }

    // pre: int idx cannot be a negative value or out of bounds of the hand.
    // post: Searches the hand for any new aces starting from the given idx parameter.
    //       the ace values would be changed depending on the player's hand. The parameter
    //       idx represents the starting idx for searching.
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

    // post: Removes all of the cards from the hand and split hand.
    public void clear() {
        hand.clear();
        splitHand.clear();
    }

    // post: Returns the ID name of the player.
    public String getId() {
        return pid;
    }

    // post: Returns the player's funds.
    public int getFunds() {
        return this.funds;
    }

    // post: Sets the player fund value to the passed in funds parameter. The parameter
    //       funds represents the amount of money the player has available to bid.
    public void setFunds(int funds) {
        this.funds = funds;
    }

    // post: Returns the value of the hand bid.
    public int getBid() {
        return this.bid;
    }

    // post: Returns the value of the split hand bid.
    public int getSplitBid() {
        return this.splitBid;
    }

    // post: Sets the value of the hand bid to the given bid value. The parameter
    //       int bid represents the amount of funds the player is wagering.
    public void setBid(int bid) {
        this.bid = bid;
    }

    // post: Sets the value of the split hand bid to the given bid value. The parameter
    //       int bid represents the amount of funds the player is wagering.
    public void setSplitBid(int bid) {
        this.splitBid = bid;
    }
}
