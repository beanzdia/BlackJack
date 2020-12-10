package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Gameplay extends JPanel {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    public RectButton hitButton;
    public RectButton holdButton;
    public RectButton hitSplitButton;
    public RectButton holdSplitButton;
    public RectButton textButton;
    public RectButton rectOne;
    public RectButton rectEleven;
    public RectButton yesButton;
    public RectButton noButton;
    public OvalButton thousandsButton;
    public OvalButton hundredsButton;
    public OvalButton tensButton;
    public OvalButton onesButton;
    public OvalButton doubleButton;
    public OvalButton bidButton;
    public Deck deck;
    public Player player;
    public Player dealer;
    private DeckGenerator genPlayerDeck;
    private DeckGenerator genPlayerSplitDeck;
    private DeckGenerator genDealerDeck;
    public boolean game = true;
    public boolean splitGame = false;
    public boolean bid = false;
    public boolean splitBid = false;
    TextAlignment textAlign = new TextAlignment();

    Deck test = new Deck();

    public Gameplay() {
        hitButton = new RectButton(WIDTH / 2 - 100 - 20,400,100, 50, "Hit!");
        holdButton = new RectButton(this.WIDTH / 2 + 20,400,100, 50, "Hold!");
        hitSplitButton = new RectButton(this.WIDTH / 2 + 50,400,100, 50, "Hit!");
        holdSplitButton = new RectButton(this.WIDTH / 2 + 50 + 100 + 40,400,100, 50, "Hold!");
        textButton = new RectButton(200,266 + 108 / 2 - 35 / 2 - 50,435, 35, " Click here to Continue");
        rectOne = new RectButton(this.WIDTH - 200 - 10, this.HEIGHT / 2 - 150 + 100 - 50 -10,75,50, "1");
        rectEleven = new RectButton(this.WIDTH - 200 - 10 + 20 + 75, this.HEIGHT / 2 - 150 + 100 - 50 - 10,75,50, "11");
        yesButton = new RectButton(this.WIDTH - 200 - 10, this.HEIGHT / 2 - 150 + 100 - 50 -10,75,50, "Yes");
        noButton = new RectButton(this.WIDTH - 200 - 10 + 20 + 75, this.HEIGHT / 2 - 150 + 100 - 50 - 10,75,50, "No");

        // Creating bid buttons
        thousandsButton = new OvalButton(this.WIDTH / 2 + 75 + 30,475,75,75, "$1000");
        hundredsButton = new OvalButton(this.WIDTH / 2 + 10,475,75,75, "$100");
        tensButton = new OvalButton(this.WIDTH / 2 - 75 - 10,475,75,75, "$10");
        onesButton = new OvalButton(this.WIDTH / 2 - 75*2 - 30,475,75,75,"$1");
        doubleButton = new OvalButton(this.WIDTH / 2 + 75*2 + 50 + 10,475,50,50, "double");
        bidButton = new OvalButton(this.WIDTH / 2 - 75*2 - 50*2 - 10,475,50,50, "bid");

        // Creating a deck
        deck = new Deck();
        deck.fillDeck();
        deck.shuffle();

        // Creating a test deck
        test.add(new Card(Card.Suit.SPADES, Card.Rank.FOUR));
        test.add(new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR));
        test.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        test.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));

        // Create player and dealer hand
        player = new Player("You");
        dealer = new Player("Dealer");
        dealer.add(deck.draw());
        Card dealerCardTwo = deck.draw();
        dealerCardTwo.setFaceUp(false);
        dealer.add(dealerCardTwo);
    }

    public void paint(Graphics g) {
        if (player.handValue() >= 21) {
            game = false;
        }
        if (player.splitHandValue() >= 21) {
            splitGame = false;
        }

        // draw the background
        g.setColor(Color.green);
        g.fillRect(0,0,700, 600);

        // draw the title text
        g.setColor(Color.black);
        textAlign.drawCenteredString(new Font("serif", Font.BOLD, 52), 700, 600, "BlackJack", g);

        // draw the player's hand
        genPlayerDeck = new DeckGenerator(player);
        if (!player.split) {
            genPlayerDeck.draw((Graphics2D) g);
        } else {
            genPlayerDeck.splitDraw((Graphics2D) g);
        }
        // draw the dealer's hand
        genDealerDeck = new DeckGenerator(dealer);
        genDealerDeck.dealerDraw((Graphics2D) g, game, splitGame);

        if (player.split) {
            genPlayerSplitDeck = new DeckGenerator(player);
        }

        // draws the background which includes the buttons and poker chips
        Font fontTwentyFour = new Font("serif", Font.BOLD, 24);
        if (player.split) {
            hitButton.x = this.WIDTH / 2 - 50 - 100 - 40 - 100;
            holdButton.x = this.WIDTH / 2 - 50 - 100;
            hitSplitButton.draw(g, Color.lightGray, fontTwentyFour);
            holdSplitButton.draw(g, Color.lightGray, fontTwentyFour);
        }
        hitButton.draw(g, Color.lightGray, fontTwentyFour);
        holdButton.draw(g, Color.lightGray, fontTwentyFour);

        Font fontTwenty = new Font("serif", Font.PLAIN, 20);
        Font fontSixteen = new Font("serif", Font.PLAIN, 16);
        thousandsButton.draw(g, Color.orange, fontTwentyFour);
        hundredsButton.draw(g, Color.orange, fontTwentyFour);
        tensButton.draw(g, Color.orange, fontTwentyFour);
        onesButton.draw(g, Color.orange, fontTwentyFour);
        doubleButton.draw(g, Color.red, fontSixteen);
        bidButton.draw(g, Color.red, fontSixteen);

        if (player.newAce && player.handValue() < 21) {
            aceDraw((Graphics2D) g);
        }

        if(player.hand.size() == 2 && player.hand.get(0).getRank().getRankValue() == player.hand.get(1).getRank().getRankValue() && !player.split
            && player.getBid() * 2 <= player.getFunds()) {
            splitDraw((Graphics2D) g);
        }

        if (!bid) {
            noBidDraw((Graphics2D) g);
        }

        if (!game && !splitGame) {
            dealer.getHand().get(1).setFaceUp(true);
            dealerHit();
            gameSplitResult();
            gameResult((Graphics2D) g);
            repaint();
        }
        g.dispose();
    }

    // Prints the winner, if the dealer drew any additional cards
    public void gameResult(Graphics2D g) {
        Font font = new Font("serif", Font.BOLD, 28);
        g.setColor(Color.white);
        g.fill(textButton);
        g.setColor(Color.black);
        String result = gameHelperResult();
        if (player.split) {
            textAlign.drawCenteredString(g, textButton.getText(), textButton, font);
        } else {
            textAlign.drawCenteredString(g, result + textButton.getText(), textButton, font);
        }
        if (player.getFunds() == 0) {
            g.setColor(Color.white);
            g.fillRect(200,266 + 108 / 2 - 35 / 2 + 35,435, 35);
            g.setColor(Color.black);
            textAlign.drawCenteredString(g,"Ran out of Funds!", new Rectangle2D.Double(200,266 + 108 / 2 - 35 / 2 + 35,435, 35),
                    font);
        }
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

    public void gameSplitResult() {
        int dealerHand = dealer.handValue();
        int hand = player.handValue();
        int splithand = player.splitHandValue();
        if ((dealerHand > hand || hand > 21) && dealerHand <= 21) {
            player.setFunds(player.getFunds() - player.getBid());
        } else if ((dealerHand < hand || dealerHand > 21) && hand <= 21) {
            player.setFunds(player.getFunds() + player.getBid());
        }
        if ((dealerHand > splithand || splithand > 21) && dealerHand <= 21) {
            player.setFunds(player.getFunds() - player.getSplitBid());
        } else if ((dealerHand < splithand || dealerHand > 21) && splithand <= 21) {
            player.setFunds(player.getFunds() + player.getSplitBid());
        }
        player.setBid(0);
        player.setSplitBid(0);
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

    public void splitDraw(Graphics2D g) {
        g.setColor(Color.gray);
        Rectangle2D temp = new Rectangle2D.Double(this.WIDTH - 200 - 25, this.HEIGHT / 2 - 150,200,125);
        g.fill(temp);
        g.setColor(Color.white);
        textAlign.drawCenteredStringTop(g, "Split Hand? ", temp, new Font("serif", Font.BOLD, 22));
        yesButton.draw(g,Color.yellow, new Font("serif", Font.BOLD, 28));
        noButton.draw(g,Color.yellow, new Font("serif", Font.BOLD, 28));
    }
}
