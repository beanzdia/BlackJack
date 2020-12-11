// The DeckGenerator class generates and draws the cards in the hand of a player.
// It also draws the cards in the split hand when appropriate. The hand of a dealer
// can also be drawn.

package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeckGenerator extends Component {
    // The width of the Blackjack cards
    private int cardWidth = 69;
    // Deck stores the Cards in the hand of a player/dealer
    private ArrayList<Card> deck;
    // splitDeck stores the Cards in the split hand of a player
    private ArrayList<Card> splitDeck;
    // player represents either a user/player or dealer
    private Player player;

    // pre: Player player cannot be null.
    // post: DeckGenerator represents the card objects in the player's hand and split hand.
    //       The parameter player represents either the player or the dealer.
    public DeckGenerator(Player player) {
        this.deck = player.hand;
        this.splitDeck = player.splitHand;
        this.player = player;
    }

    // pre: Graphics2D g cannot be null
    // post: Draw the cards in the player's hand and split hand with appropriate hand, funds,
    // and bid value. Accepts a parameter g which creates and draws the game objects. The
    // parameter split represents whether the player has a split hand or only one hand.
    public void draw(Graphics2D g, boolean split) {
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("Hand: " + player.handValue(), 20,266 + 50);
        g.drawString("Funds: $" + player.getFunds(), 20,266 + 75);
        g.drawString("Bid: $" + player.getBid(), 20, 266 + 100);
        if (split) {
            g.drawString("Hand: " + player.splitHandValue(), 280 + 75,266 + 50);
            g.drawString("Bid: $" + player.getSplitBid(), 280 + 75,266 + 75);
            g.setColor(Color.white);
            for (int i = 0; i < deck.size(); i++) {
                ImageIcon temp = deck.get(i).drawCardImage();
                temp.paintIcon(this,g, 200 + (cardWidth / 8) * i, 266);
            }
            for (int i = 0; i < splitDeck.size(); i++) {
                ImageIcon temp = splitDeck.get(i).drawCardImage();
                temp.paintIcon(this,g, 500 + (cardWidth / 8) * i, 266);
            }
        } else {
            for (int i = 0; i < deck.size(); i++) {
                ImageIcon temp = deck.get(i).drawCardImage();
                temp.paintIcon(this,g, 200 + (cardWidth / 2) * i, 266);
            }
        }
    }

    // pre: Graphics2D g cannot be null
    // post: Draw the cards in the dealer's hand and split hand with appropriate hand, funds,
    // and bid value. Accepts a parameter g which creates and draws the game objects. The
    // parameter game and split game represent when the user/player has busted/held on
    // their hand and split hand.
    public void dealerDraw(Graphics2D g, boolean game, boolean splitGame) {
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 30));
        if (game || splitGame) {
            g.drawString("Hand: " + player.hand.get(0).getRank().getRankValue(), 20, 150);
        } else {
            g.drawString("Hand: " + player.handValue(), 20, 150);
        }
        g.setColor(Color.white);
        for (int i = 0; i < deck.size(); i++) {
            ImageIcon temp = deck.get(i).drawCardImage();
            temp.paintIcon(this,g, 200 + (cardWidth / 2) * i, 125);
        }
    }
}
