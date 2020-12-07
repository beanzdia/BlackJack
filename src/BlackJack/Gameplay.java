package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Gameplay extends JPanel {
    private int width;
    private int height;
    private ImageIcon titleImage;
    private Rectangle2D hitButton;
    private Rectangle2D holdButton;
    private Rectangle2D textButton;
    private Rectangle2D rectOne;
    private Rectangle2D rectEleven;
    private Ellipse2D thousandsButton;
    private Ellipse2D hundredsButton;
    private Ellipse2D tensButton;
    private Ellipse2D onesButton;
    private Ellipse2D doubleButton;
    private Deck deck;
    private Player player;
    private Player dealer;
    private DeckGenerator genPlayerDeck;
    private DeckGenerator genDealerDeck;
    private boolean game = true;
    private boolean splitHand = false;
    private boolean newAce = false;
    TextAlignment textAlign = new TextAlignment();
    private ArrayList<DeckGenerator> decks;

    public Gameplay(int width, int height) {
        this.width = width;
        this.height = height;
        hitButton = new Rectangle2D.Double(this.width / 2 - 100 - 20,400,100, 50);
        holdButton = new Rectangle2D.Double(this.width / 2 + 20,400,100, 50);
        textButton = new Rectangle2D.Double(200,266 + 108 / 2 - 35 / 2,435, 35);
        rectOne = new Rectangle2D.Double(this.width - 200 - 10, this.height / 2 - 150 + 100 - 50 -10,75,50);
        rectEleven = new Rectangle2D.Double(this.width - 200 - 10 + 20 + 75, this.height / 2 - 150 + 100 - 50 - 10,75,50);
        thousandsButton = new Ellipse2D.Double(this.width / 2 + 75 + 30,475,75,75);
        hundredsButton = new Ellipse2D.Double(this.width / 2 + 10,475,75,75);
        tensButton = new Ellipse2D.Double(this.width / 2 - 75 - 10,475,75,75);
        onesButton = new Ellipse2D.Double(this.width / 2 - 75*2 - 30,475,75,75);
        doubleButton = new Ellipse2D.Double(this.width / 2 + 75*2 + 50 + 10,475,50,50);
        // Creating a deck
        deck = new Deck();
        deck.fillDeck();
        deck.shuffle();

        // Create player hand
        player = new Player("You");
        //player.add(deck.draw());
        //player.add(deck.draw());

        // Create dealer hand
        dealer = new Player("Dealer");
        Card d2Card1 = deck.draw();
        Card d2Card2 = deck.draw();
        d2Card2.setFaceUp(false);
        dealer.add(d2Card1);
        dealer.add(d2Card2);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println(e);
                //
                if ((e.getButton() == 1) && holdButton.contains(e.getX(), e.getY()) && game && player.hand.size() >= 2 && !newAce) {
                    game = false;
                    dealer.getHand().get(1).setFaceUp(true);
                    repaint();
                }
                // red is hold
                if ((e.getButton() == 1) && hitButton.contains(e.getX(), e.getY()) && game && !newAce) {
                    Card temp = deck.draw();
                    if (temp.getRank().getRankLabel().equals("ace")) {
                        newAce = true;
                    }
                    player.add(temp);
                    if (deck.deck.isEmpty()) {
                        deck.deck.clear();
                        deck.fillDeck();
                        deck.shuffle();
                    }
                    repaint();
                }
                // text for when game is over should allow palyers to start the next game
                if ((e.getButton() == 1) && textButton.contains(e.getX(), e.getY()) && !game) {
                    player.clear();
                    dealer.clear();
                    Card d2Card1 = deck.draw();
                    Card d2Card2 = deck.draw();
                    d2Card2.setFaceUp(false);
                    dealer.add(d2Card1);
                    dealer.add(d2Card2);
                    game = true;
                    repaint();
                }

                // if 5 oval
                if ((e.getButton() == 1) && thousandsButton.contains(e.getX(), e.getY())) {
                    player.setBid(player.getBid() + 1000);
                    repaint();
                }
                if ((e.getButton() == 1) && hundredsButton.contains(e.getX(), e.getY())) {
                    player.setBid(player.getBid() + 100);
                    repaint();
                }
                if ((e.getButton() == 1) && tensButton.contains(e.getX(), e.getY())) {
                    player.setBid(player.getBid() + 10);
                    repaint();
                }
                if ((e.getButton() == 1) && onesButton.contains(e.getX(), e.getY())) {
                    player.setBid(player.getBid() + 1);
                    repaint();
                }
                if ((e.getButton() == 1) && rectOne.contains(e.getX(), e.getY())) {
                    Card ace = player.hand.get(player.hand.size() - 1);
                    player.hand.set(player.hand.size() - 1, new Card(ace.getSuit(), Card.Rank.ACE1));
                    newAce = false;
                    repaint();
                }
                if ((e.getButton() == 1) && rectEleven.contains(e.getX(), e.getY())) {
                    Card ace = player.hand.get(player.hand.size() - 1);
                    player.hand.set(player.hand.size() - 1, new Card(ace.getSuit(), Card.Rank.ACE11));
                    newAce = false;
                    repaint();
                }
            }
        });
    }

    public void paint(Graphics g) {
        if (player.handValue() >= 21) {
            game = false;
        }

        // draw the background
        g.setColor(Color.green);
        g.fillRect(0,0,700, 600);

        // draw the title text
        g.setColor(Color.black);
        textAlign.drawCenteredString(new Font("serif", Font.BOLD, 52), 700, 600, "BlackJack", g);

        // draw the player's hand
        genPlayerDeck = new DeckGenerator(player);
        genPlayerDeck.draw((Graphics2D) g);

        // draw the dealer's hand
        genDealerDeck = new DeckGenerator(dealer);
        genDealerDeck.dealerDraw((Graphics2D) g, game);

        // draws the background which includes the buttons
        // and poker chips
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.lightGray);
        g2d.fill(hitButton);
        g2d.setColor(Color.black);
        textAlign.drawCenteredString(g, "Hit!", hitButton, new Font("serif", Font.BOLD, 24));
        g2d.setColor(Color.lightGray);
        g2d.fill(holdButton);
        g2d.setColor(Color.black);
        // g2d.drawString("Hold!",420,435);
        textAlign.drawCenteredString(g, "Hold!", holdButton, new Font("serif", Font.BOLD, 24));
        g2d.setColor(Color.orange);
        g2d.fill(thousandsButton);
        g2d.fill(hundredsButton);
        g2d.fill(tensButton);
        g2d.fill(onesButton);
        g2d.setColor(Color.red);
        g2d.fill(doubleButton);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("serif", Font.PLAIN, 20));
        textAlign.drawCenteredStringOval(g, "$1000", thousandsButton, new Font("serif", Font.PLAIN, 20));
        textAlign.drawCenteredStringOval(g, "$100", hundredsButton, new Font("serif", Font.PLAIN, 20));
        textAlign.drawCenteredStringOval(g, "$10", tensButton, new Font("serif", Font.PLAIN, 20));
        textAlign.drawCenteredStringOval(g, "$1", onesButton, new Font("serif", Font.PLAIN, 20));
        textAlign.drawCenteredStringOval(g, "double", doubleButton, new Font("serif", Font.PLAIN, 16));

        if (newAce) {
            aceDraw((Graphics2D) g);
        }

        if (!game) {
            g2d.setFont(new Font("serif", Font.BOLD, 28));
            dealer.getHand().get(1).setFaceUp(true);
            dealerHit();
            repaint();
            gameResult((Graphics2D) g);
        }
        g.dispose();
    }

    // Prints the winner, if the dealer drew any additional cards
    public void gameResult(Graphics2D g) {
        g.setColor(Color.white);
        g.fill(textButton);
        g.setColor(Color.black);
        textAlign.drawCenteredString(g, gameHelperResult() + " Click here to Continue", textButton, new Font("serif", Font.BOLD, 28));
    }
    // Returns a string for all of the win/lose/push scenarios
    private String gameHelperResult() {
        int dealerHand = dealer.handValue();;
        int p1Hand = player.handValue();
        if (dealerHand > p1Hand && dealerHand <= 21) {
           return "Dealer wins!";
        } else if (dealerHand < p1Hand && p1Hand <= 21) {
            return "Player wins!";
        } else if (p1Hand > 21 && dealerHand <= 21) {
            return "Dealer wins!";
        } else if (dealerHand > 21 && p1Hand <= 21) {
            return "Player wins!";
        } else if ((dealerHand == p1Hand) && dealerHand <= 21) {
            return "Push!";
        } else {
            return "Player loses hand";
        }
    }

    // Logic for dealer hitting on their hand
    public void dealerHit() {
        int dealerHand = dealer.handValue();
        int p1Hand = player.handValue();
        int idxAce = dealer.indexOfNewAce();
        if (idxAce != -1) {
            if (dealerHand <= 11) {
                dealer.hand.get(idxAce).getRank().setRankValue(11);
            } else {
                dealer.hand.get(idxAce).getRank().setRankValue(1);
            }
        }
        dealerHand = dealer.handValue();
        if (p1Hand < 21) {
            while (dealerHand < 21 && dealerHand < p1Hand) {
                dealer.add(deck.draw());
                dealerHand = dealer.handValue();
            }
        }
    }

    // Draws the menu for an ace card
    public void aceDraw(Graphics2D g) {
        g.setColor(Color.gray);
        Rectangle2D temp = new Rectangle2D.Double(this.width - 200 - 25, this.height / 2 - 150,200,125);
        g.fill(temp);
        g.setColor(Color.black);
        textAlign.drawCenteredStringTop(g, "Click on Ace Value:", temp, new Font("serif", Font.BOLD, 22));
        g.setColor(Color.blue);
        g.fill(rectOne);
        g.fill(rectEleven);
        g.setColor(Color.black);
        textAlign.drawCenteredString(g,"1",rectOne, new Font("serif", Font.BOLD, 28));
        textAlign.drawCenteredString(g,"11",rectEleven, new Font("serif", Font.BOLD, 28));
    }
}
