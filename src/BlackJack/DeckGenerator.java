package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeckGenerator extends Component {
    private boolean isSplit;
    private int value;

    public int cardWidth = 69;
    public int cardHeight = 108;
    public int hSpacing = 10;
    public int vSpacing = 50;
    public int n;
    public ArrayList<Card> deck;
    public ArrayList<Card> splitDeck;
    public Player player;
    TextAlignment textAlign;
    public ArrayList<Card> splitHand;
    public boolean finishSplit;

    public DeckGenerator(Player player) {
        TextAlignment textAlign = new TextAlignment();
        splitHand = new ArrayList<Card>();
        this.finishSplit = false;
        this.isSplit = false;
        this.deck = player.getHand();
        this.player = player;
    }

    // Draw the players hand when the player is a player
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("Hand: " + player.handValue(), 20,266 + 50);
        g.drawString("Funds: $" + player.getFunds(), 20,266 + 75);
        g.drawString("Bid: $" + player.getBid(), 20, 266 + 100);
        g.setColor(Color.white);
//        if (isSplittable()) {
//            for (int i = 0; i < deck.size(); i++) {
//                splitHand.set(deck.size() - 1, drawCard);
//                ImageIcon temp = splitHand.get(i).drawCardImage();
//                temp.paintIcon(this, g, 400 + (cardWidth / 2) * i, 266);
//            }
//        }
        for (int i = 0; i < deck.size(); i++) {
            ImageIcon temp = deck.get(i).drawCardImage();
            temp.paintIcon(this,g, 200 + (cardWidth / 2) * i, 266);
        }
    }

//    public boolean isSplittable() {
//        return deck.size() == 2
//                && deck.get(0).getRank().getRankValue() == deck.get(1).getRank().getRankValue()
//                && !isSplit;
//    }
//
//    public ArrayList<Card> split() {
//            splitHand = new ArrayList<Card>();
//            splitHand.add(deck.remove(1));
//            value = deck.get(0).getRank().getRankValue();
//            isSplit = true;
//            finishSplit = true;
//            return splitHand;
//    }

    // Draw the dealers hand when the player is the dealer
    public void dealerDraw(Graphics2D g, boolean game) {
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 30));
        if (game) {
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
