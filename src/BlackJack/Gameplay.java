// The Gameplay generates and draws the background and game object in BlackJack. The game objects
// include buttons for setting a bid, hitting for a card, holding a hand, splitting a hand,
// and selecting an ace. The class also generates and updates the cards in the dealer and
// players hand.

package BlackJack;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Gameplay extends JPanel {
    // WIDTH and HEIGHT represent the dimensions of the window generated
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    // hitButton and holdButton allow the user to draw another card and end their hand.
    public RectButton hitButton;
    public RectButton holdButton;
    // hitSplitButton and holdSlitButton allow user to draw another card and end their split hand.
    public RectButton hitSplitButton;
    public RectButton holdSplitButton;
    // textButton allows the user to reset the game after a hand has ended
    public RectButton textButton;
    // rectOne and rectEleven are buttons that allow the user to choose either a 1 or 11 ace value
    public RectButton rectOne;
    public RectButton rectEleven;
    // yesButton allows the user to input that they want to split their hand
    public RectButton yesButton;
    // thousandButton, hundredsButton, tensButton, and onesButton allow the user to input their hand bet in
    // increments of $1000, $100, $10, and $1.
    public OvalButton thousandsButton;
    public OvalButton hundredsButton;
    public OvalButton tensButton;
    public OvalButton onesButton;
    // doubleButton allows users to double their bet
    public OvalButton doubleButton;
    // bidButton allows users to place their bid
    public OvalButton bidButton;
    // deck represents a standard card deck for Blackjack
    public Deck deck;
    // player represents the user's game attributes
    public Player player;
    // dealer represents the dealer's game attributes
    public Player dealer;
    // game indicates whether the player's hand is holding
    public boolean game = true;
    // splitGame indicates whether the player's split hand is holding
    public boolean splitGame = false;
    // bid indicates whether the user has inputted a bet
    public boolean bid = false;
    // textAlign generates text that is aligned within ovals and rectangles
    TextAlignment textAlign = new TextAlignment();

    public Deck test = new Deck();

    // post: Gameplay constructs the game buttons, card deck, dealer, and player.
    public Gameplay() {
        // Creating the buttons for hitting and holding with and without splitting a hand
        hitButton = new RectButton(WIDTH / 2 - 100 - 20,400,100, 50, "Hit!");
        holdButton = new RectButton(this.WIDTH / 2 + 20,400,100, 50, "Hold!");
        hitSplitButton = new RectButton(this.WIDTH / 2 + 50,400,100, 50, "Hit!");
        holdSplitButton = new RectButton(this.WIDTH / 2 + 50 + 100 + 40,400,100, 50, "Hold!");

        // Creating the button for the pop-up menu to reset a hand
        textButton = new RectButton(200,266 + 108 / 2 - 35 / 2 - 50,435, 35, " Click here to Continue");

        // Creating the button for the pop-up menu to set an ace card's value
        rectOne = new RectButton(this.WIDTH - 200 - 10, this.HEIGHT / 2 - 150 + 100 - 50 -10,75,50, "1");
        rectEleven = new RectButton(this.WIDTH - 200 - 10 + 20 + 75, this.HEIGHT / 2 - 150 + 100 - 50 - 10,75,50, "11");

        // Creating the button for the pop-up menu to split a hand
        yesButton = new RectButton(this.WIDTH - 200 - 25 + 100 - 75/2, this.HEIGHT / 2 - 150 + 100 - 50 -10,75,50, "Yes");

        // Creating the buttons to increment a bid and finalize a bid
        thousandsButton = new OvalButton(this.WIDTH / 2 + 75 + 30,475,75,75, "$1000");
        hundredsButton = new OvalButton(this.WIDTH / 2 + 10,475,75,75, "$100");
        tensButton = new OvalButton(this.WIDTH / 2 - 75 - 10,475,75,75, "$10");
        onesButton = new OvalButton(this.WIDTH / 2 - 75*2 - 30,475,75,75,"$1");
        doubleButton = new OvalButton(this.WIDTH / 2 + 75*2 + 50 + 10,475,50,50, "double");
        bidButton = new OvalButton(this.WIDTH / 2 - 75*2 - 50*2 - 10,475,50,50, "bid");

        // Creating a new shuffled Blackjack deck
        deck = new Deck();
        deck.fillDeck();
        deck.shuffle();

        // Creating the player and dealer
        player = new Player("You");
        dealer = new Player("Dealer");

        // Adding two cards to the dealer with the second card face down
        dealer.add(deck.draw());
        Card dealerCardTwo = deck.draw();
        dealerCardTwo.setFaceUp(false);
        dealer.add(dealerCardTwo);
    }

    // pre: Graphics g must not be null
    // post: Accepts a parameter g which creates and draws the game objects.
    public void paint(Graphics g) {
        // Determines whether the player's hand busted
        if (player.handValue() >= 21) {
            game = false;
        }

        // Determines whether the split hand busted
        if (player.splitHandValue() >= 21) {
            splitGame = false;
        }

        // Draw the background
        g.setColor(Color.green);
        g.fillRect(0,0,700, 600);

        // Draw the title text
        g.setColor(Color.black);
        Font fontTitle = new Font("serif", Font.BOLD, 52);
        FontMetrics metrics = g.getFontMetrics(fontTitle);
        g.setFont(fontTitle);
        g.drawString("BlackJack",(700 - metrics.stringWidth("BlackJack")) / 2, 75);

        // Draw the player's hand
        DeckGenerator genPlayerDeck = new DeckGenerator(player);
        genPlayerDeck.draw((Graphics2D) g, player.split);

        // Draw the dealer's hand
        DeckGenerator genDealerDeck = new DeckGenerator(dealer);
        genDealerDeck.dealerDraw((Graphics2D) g, game, splitGame);

        // Draws the background which includes the buttons and poker chips
        Font fontTwentyFour = new Font("serif", Font.BOLD, 24);
        if (player.split) {
            hitButton.x = this.WIDTH / 2 - 50 - 100 - 40 - 100;
            holdButton.x = this.WIDTH / 2 - 50 - 100;
            hitSplitButton.draw(g, Color.lightGray, fontTwentyFour);
            holdSplitButton.draw(g, Color.lightGray, fontTwentyFour);
        }
        hitButton.draw(g, Color.lightGray, fontTwentyFour);
        holdButton.draw(g, Color.lightGray, fontTwentyFour);
        Font fontSixteen = new Font("serif", Font.PLAIN, 16);
        thousandsButton.draw(g, Color.orange, fontTwentyFour);
        hundredsButton.draw(g, Color.orange, fontTwentyFour);
        tensButton.draw(g, Color.orange, fontTwentyFour);
        onesButton.draw(g, Color.orange, fontTwentyFour);
        doubleButton.draw(g, Color.red, fontSixteen);
        bidButton.draw(g, Color.red, fontSixteen);

        // Draw and prompts the player whether they want to change a new ace's value in their hand or split hand
        if (player.newAce && player.handValue() < 21) {
            aceDraw((Graphics2D) g);
        }
        if (player.splitHand != null) {
            if (player.newSplitAce && player.splitHandValue() < 21) {
                aceDraw((Graphics2D) g);
            }
        }

        // Draw and prompts the player whether they want to split their hand after being dealt two cards of equivalent rank
        if(player.hand.size() == 2 && player.hand.get(0).getRank().getRankLabel().equals(player.hand.get(1).getRank().getRankLabel())
            && player.getBid() * 2 <= player.getFunds()) {
            splitDraw((Graphics2D) g);
        }

        // Draw and prompts user to enter a bid
        if (!bid) {
            noBidDraw((Graphics2D) g);
        }

        // Draws a message determining the results of the hand and split hand.
        if (!game && !splitGame) {
            dealer.hand.get(1).setFaceUp(true);
            dealerHit();
            fundUpdate();
            gameResult((Graphics2D) g);
            repaint();
        }

        g.dispose();
    }

    // pre: Graphics2D g must not be null
    // post: Draws a message indicating the results of a player's hand. A player can win/lose/push depending on the
    //       dealer's hand. Also resets the game when funds reach zero dollars. Accepts a parameter g which creates and draws
    //       the game objects.
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
        } else {
            return "Push!";
        }
    }

    // post: Updates the funds of the player's hand and split hand depending on whether they lost their bet,
    //       are receiving a payout from the dealer, or pushed.
    public void fundUpdate() {
        int dealerHand = dealer.handValue();
        int hand = player.handValue();
        int splitHand = player.splitHandValue();
        if ((dealerHand > hand || hand > 21) && dealerHand <= 21) {
            player.setFunds(player.getFunds() - player.getBid());
        } else if ((dealerHand < hand || dealerHand > 21) && hand <= 21) {
            player.setFunds(player.getFunds() + player.getBid());
        }
        player.setBid(0);
        if (player.splitHand != null) {
            if ((dealerHand > splitHand || splitHand > 21) && dealerHand <= 21) {
                player.setFunds(player.getFunds() - player.getSplitBid());
            } else if ((dealerHand < splitHand || dealerHand > 21) && splitHand <= 21) {
                player.setFunds(player.getFunds() + player.getSplitBid());
            }
            player.setSplitBid(0);
        }
    }

    // post: Dealer can hold or increase their hand to try and beat the player's hand.
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

    // pre: Graphics2D g must not be null
    // post: Draws a pop-up window that prompts the player to select whether the ace should be a one or eleven. Accepts a parameter g which creates and draws
    //       the game objects.
    public void aceDraw(Graphics2D g) {
        g.setColor(Color.gray);
        Rectangle2D temp = new Rectangle2D.Double(this.WIDTH - 200 - 25, this.HEIGHT / 2 - 150,200,125);
        g.fill(temp);
        g.setColor(Color.black);
        textAlign.drawCenteredStringTop(g, "Click on Ace Value:", temp, new Font("serif", Font.BOLD, 22));
        rectOne.draw(g,Color.blue, new Font("serif", Font.BOLD, 28));
        rectEleven.draw(g,Color.blue, new Font("serif", Font.BOLD, 28));
    }

    // pre: Graphics2D g must not be null
    // post: Draws a pop-up window that prompts the user to enter a bid. Accepts a parameter g which creates and draws
    //       the game objects.
    public void noBidDraw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(200,266 + 108 / 2 - 35 / 2,435, 35);
        g.setColor(Color.black);
        textAlign.drawCenteredString(g, "Please enter a bid:", new Rectangle2D.Double(200,266 + 108 / 2 - 35 / 2,435, 35), new Font("serif", Font.BOLD, 28));
    }

    // pre: Graphics2D g must not be null
    // post: Draws a pop-up window that prompts the user whether they want to split their hand. Accepts a parameter g which creates and draws
    //       the game objects.
    public void splitDraw(Graphics2D g) {
        g.setColor(Color.gray);
        Rectangle2D temp = new Rectangle2D.Double(this.WIDTH - 200 - 25, this.HEIGHT / 2 - 150,200,125);
        g.fill(temp);
        g.setColor(Color.white);
        textAlign.drawCenteredStringTop(g, "Split Hand? ", temp, new Font("serif", Font.BOLD, 22));
        yesButton.draw(g,Color.yellow, new Font("serif", Font.BOLD, 28));
    }
}
