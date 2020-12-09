package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeckGenerator extends Component {
    public int cardWidth = 69;
    public int cardHeight = 108;
    public int hSpacing = 10;
    public int vSpacing = 50;
    public int n;
    public ArrayList<Card> deck;
    public ArrayList<Card> splitDeck;
    public Player player;
    public Rectangle2D yesButton;
    public Rectangle2D noButton;
    public Rectangle2D rectOne;
    public Rectangle2D rectEleven;
    TextAlignment textAlign;
    private boolean splitHand;

    public DeckGenerator(Player player) {
        TextAlignment textAlign = new TextAlignment();
        this.splitHand = splitHand;
        this.deck = player.getHand();
        this.splitDeck = player.getSplitHand();
        this.player = player;
        yesButton = new Rectangle2D.Double(125, 150,75,50);
        noButton = new Rectangle2D.Double(225, 150,75,50);
    }

    // Draw the players hand when the player is a player
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("Hand: " + player.handValue(), 20,266 + 50);
        g.drawString("Funds: $" + player.getFunds(), 20,266 + 75);
        g.drawString("Bid: $" + player.getBid(), 20, 266 + 100);
        g.setColor(Color.white);
        for (int i = 0; i < deck.size(); i++) {
            ImageIcon temp = deck.get(i).drawCardImage();
            temp.paintIcon(this,g, 200 + (cardWidth / 2) * i, 266);
        }
    }

    // Draw the dealers hand when the player is the dealer
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

    // potential splitdraw method
    public void splitDraw(Graphics2D g) {
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 26));
        g.drawString("Hand: " + player.handValue(), 20,266 + 50);
        g.drawString("Funds: $" + player.getFunds(), 20,266 + 75);
        g.drawString("Bid: $" + player.getBid(), 20, 266 + 100);
        g.drawString("Hand: " + player.splitHandValue(), 300 + 75,266 + 50);
        g.drawString("Bid: $" + player.getSplitBid(), 300 + 75,266 + 75);
        g.setColor(Color.white);
        for (int i = 0; i < deck.size(); i++) {
            ImageIcon temp = deck.get(i).drawCardImage();
            temp.paintIcon(this,g, 200 + (cardWidth / 8) * i, 266);
        }
        for (int i = 0; i < splitDeck.size(); i++) {
            ImageIcon temp = splitDeck.get(i).drawCardImage();
            temp.paintIcon(this,g, 500 + (cardWidth / 8) * i, 266);
        }
    }
}
