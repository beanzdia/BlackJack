package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Gameplay extends JPanel {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    public RectButton hitButton;
    public RectButton holdButton;
    public RectButton textButton;
    public RectButton rectOne;
    public RectButton rectEleven;
    public OvalButton thousandsButton;
    public OvalButton hundredsButton;
    public OvalButton tensButton;
    public OvalButton onesButton;
    public OvalButton doubleButton;
    public Deck deck;
    public Player player;
    public Player dealer;
    private DeckGenerator genPlayerDeck;
    private DeckGenerator genDealerDeck;
    public boolean game = true;
    public boolean bid = true;
    TextAlignment textAlign = new TextAlignment();

    public Gameplay() {
        hitButton = new RectButton(WIDTH / 2 - 100 - 20,400,100, 50, "Hit!");
        holdButton = new RectButton(this.WIDTH / 2 + 20,400,100, 50, "Hold!");
        textButton = new RectButton(200,266 + 108 / 2 - 35 / 2,435, 35, " Click here to Continue");
        rectOne = new RectButton(this.WIDTH - 200 - 10, this.HEIGHT / 2 - 150 + 100 - 50 -10,75,50, "1");
        rectEleven = new RectButton(this.WIDTH - 200 - 10 + 20 + 75, this.HEIGHT / 2 - 150 + 100 - 50 - 10,75,50, "11");

        // Creating bid buttons
        thousandsButton = new OvalButton(this.WIDTH / 2 + 75 + 30,475,75,75, "$1000");
        hundredsButton = new OvalButton(this.WIDTH / 2 + 10,475,75,75, "$100");
        tensButton = new OvalButton(this.WIDTH / 2 - 75 - 10,475,75,75, "$10");
        onesButton = new OvalButton(this.WIDTH / 2 - 75*2 - 30,475,75,75,"$1");
        doubleButton = new OvalButton(this.WIDTH / 2 + 75*2 + 50 + 10,475,50,50, "double");

        // Creating a deck
        deck = new Deck();
        deck.fillDeck();
        deck.shuffle();

        // Create player hand
        player = new Player("You");
        //Card p1 = new Card(Card.Suit.CLUBS, Card.Rank.TEN);
        //Card p2 = new Card(Card.Suit.CLUBS, Card.Rank.TWO);
        //player.add(p1);
        //player.add(p2);

        // Create dealer hand
        dealer = new Player("Dealer");
        /*
        Card dealerFirst = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
        Card dealerSecond = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        */
        /*
        Card dealerFirst = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
        Card dealerSecond = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
        */
        /*
        Card dealerFirst = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
        Card dealerSecond = new Card(Card.Suit.DIAMONDS, Card.Rank.KING);
        */
        /*
        Card dealerFirst = new Card(Card.Suit.CLUBS, Card.Rank.QUEEN);
        Card dealerSecond = new Card(Card.Suit.DIAMONDS, Card.Rank.ACE);
        */

        //Card dealerFirst = new Card(Card.Suit.SPADES, Card.Rank.FOUR);
        //Card dealerSecond = new Card(Card.Suit.CLUBS, Card.Rank.NINE);
        //Card dealerThird = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);
        //Card dealerFourth = new Card(Card.Suit.CLUBS, Card.Rank.ACE);


        //dealer.add(dealerFirst);
        //dealer.add(dealerSecond);
        //dealer.add(dealerThird);
        //dealer.add(dealerFourth);

        dealer.add(deck.draw());
        Card dealerCardTwo = deck.draw();
        dealerCardTwo.setFaceUp(false);
        dealer.add(dealerCardTwo);


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

        // draws the background which includes the buttons and poker chips
        Font fontA = new Font("serif", Font.BOLD, 24);
        hitButton.draw(g, Color.lightGray, fontA);
        holdButton.draw(g, Color.lightGray, fontA);

        Font font = new Font("serif", Font.PLAIN, 20);
        thousandsButton.draw(g, Color.orange, font);
        hundredsButton.draw(g, Color.orange, font);
        tensButton.draw(g, Color.orange, font);
        onesButton.draw(g, Color.orange, font);
        doubleButton.draw(g, Color.red, new Font("serif", Font.PLAIN, 16));

        if (player.newAce && player.handValue() < 21) {
            aceDraw((Graphics2D) g);
        }

        if ((player.getBid() == 0)) {
            noBidDraw((Graphics2D) g);
        }

        if (!game) {
            dealer.getHand().get(1).setFaceUp(true);
            dealerHit();
            repaint();
            gameResult((Graphics2D) g);
            bid = true;
        }
        g.dispose();
    }

    // Prints the winner, if the dealer drew any additional cards
    public void gameResult(Graphics2D g) {
        g.setColor(Color.white);
        g.fill(textButton);
        g.setColor(Color.black);
        String result = gameHelperResult();
        textAlign.drawCenteredString(g, result + textButton.getText(), textButton, new Font("serif", Font.BOLD, 28));
        player.updateFunds(result);
        if (player.getFunds() == 0) {
            g.setColor(Color.white);
            g.fillRect(200,266 + 108 / 2 - 35 / 2 + 35,435, 35);
            g.setColor(Color.black);
            textAlign.drawCenteredString(g,"Ran out of Funds!", new Rectangle2D.Double(200,266 + 108 / 2 - 35 / 2 + 35,435, 35),
                    new Font("serif", Font.BOLD, 28));
            // player.setFunds(2500);
        }
        player.setBid(0);
    }

    // Returns a string for all of the win/lose/push scenarios
    private String gameHelperResult() {
        int dealerHand = dealer.handValue();;
        int p1Hand = player.handValue();
        if ((dealerHand > p1Hand || p1Hand > 21) && dealerHand <= 21) {
           return "Dealer wins!";
        } else if ((dealerHand < p1Hand || dealerHand > 21) && p1Hand <= 21) {
            return "Player wins!";
        } else if ((dealerHand == p1Hand) && dealerHand <= 21) {
            return "Push!";
        } else {
            return "Player loses hand";
        }
    }

    // Logic for dealer hitting on their hand
    public void dealerHit() {
        dealer.containsNewAce(0);
        int dealerHand = dealer.handValue();
        int playerHand = player.handValue();
        if (playerHand < 21) {
            while (dealerHand < 21 && dealerHand < playerHand) {
                dealer.add(deck.draw());
                dealer.containsNewAce(dealer.hand.size() - 1);
                dealerHand = dealer.handValue();
            }
        }
    }

    // Draws the menu for an ace card
    public void aceDraw(Graphics2D g) {
        g.setColor(Color.gray);
        Rectangle2D temp = new Rectangle2D.Double(this.WIDTH - 200 - 25, this.HEIGHT / 2 - 150,200,125);
        g.fill(temp);
        g.setColor(Color.black);
        textAlign.drawCenteredStringTop(g, "Click on Ace Value:", temp, new Font("serif", Font.BOLD, 22));
        rectOne.draw(g,Color.blue, new Font("serif", Font.BOLD, 28));
        rectEleven.draw(g,Color.blue, new Font("serif", Font.BOLD, 28));
    }

    public void noBidDraw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(200,266 + 108 / 2 - 35 / 2,435, 35);
        g.setColor(Color.black);
        textAlign.drawCenteredString(g, "Please enter a bid:", new Rectangle2D.Double(200,266 + 108 / 2 - 35 / 2,435, 35), new Font("serif", Font.BOLD, 28));
    }
}
