// The GameListener class handles the input and game object interaction with the mouse. It is able to
// complete the actions of hitting a card by adding a card to a player hand, increasing bids, setting
// ace card values, and resetting the game.

package BlackJack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameListener extends MouseAdapter {
    // gamePlay represents the BlackJack game being played between the player and dealer
    public Gameplay gamePlay;

    // post: GameListener constructs the input listening for the game objects in gamePlay
    public GameListener (Gameplay gamePlay) {
        this.gamePlay = gamePlay;
    }

    // post: Receives mouse input and responds with actions to progress the BlackJack game.
    //       The MouseEvent e parameter represents mouse clicking on buttons in the gamePlay game.
    public void mouseClicked(MouseEvent e) {
        // The player input is received when mouse left-clicking
        if (e.getButton() == 1) {
            // The inputs for when the player hand has not held or busted is prioritized first over
            // the inputs for the button inputs for the split hand.
            if (gamePlay.game) {
                // Pressing the hold button ends the player hand
                if (gamePlay.holdButton.contains(e.getX(), e.getY()) && gamePlay.player.hand.size() >= 2 && !gamePlay.player.newAce) {
                    gamePlay.game = false;
                }
                // Pressing the hit button adds a card to the player's hand
                if (gamePlay.hitButton.contains(e.getX(), e.getY()) && !gamePlay.player.newAce && gamePlay.bid) {
                    if (gamePlay.bid) {
                        Card temp = gamePlay.deck.draw();
                        if (temp.getRank().getRankLabel().equals("ace")) {
                            gamePlay.player.newAce = true;
                        }
                        gamePlay.player.add(temp);
                        // Empty deck is filled and shuffled
                        if (gamePlay.deck.deck.isEmpty()) {
                            gamePlay.deck.deck.clear();
                            gamePlay.deck.fillDeck();
                            gamePlay.deck.shuffle();
                        }
                    }
                }
                // A player can double their bet after receiving two cards
                if (gamePlay.doubleButton.contains(e.getX(), e.getY())) {
                    if (gamePlay.player.hand.size() == 2) {
                        int bidValue = gamePlay.player.getBid();
                        if (bidValue * 2 <= gamePlay.player.getFunds()) {
                            gamePlay.player.setBid(bidValue * 2);
                        }
                    }
                }
                // A player must first enter a bid
                if (!gamePlay.bid) {
                    int handValue = gamePlay.player.getFunds();
                    int bidValue = gamePlay.player.getBid();
                    if (gamePlay.thousandsButton.contains(e.getX(), e.getY()) && handValue >= bidValue + 1000) {
                        gamePlay.player.setBid(gamePlay.player.getBid() + 1000);
                    }
                    if (gamePlay.hundredsButton.contains(e.getX(), e.getY()) && handValue >= bidValue + 100) {
                        gamePlay.player.setBid(gamePlay.player.getBid() + 100);
                    }
                    if (gamePlay.tensButton.contains(e.getX(), e.getY()) && handValue >= bidValue + 10) {
                        gamePlay.player.setBid(gamePlay.player.getBid() + 10);
                    }
                    if (gamePlay.onesButton.contains(e.getX(), e.getY()) && handValue >= bidValue + 1) {
                        gamePlay.player.setBid(gamePlay.player.getBid() + 1);
                    }
                }
                // Pressing the bid button finalizes a player's bid
                if (gamePlay.bidButton.contains(e.getX(), e.getY())) {
                    gamePlay.bid = true;
                }
                // Pressing the one button updates the ace value to a one and the eleven button updates
                // the ace value to an eleven. Pressing the yes button for splitting the hand splits the hand.
                if (gamePlay.rectOne.contains(e.getX(), e.getY()) && gamePlay.player.newAce) {
                    ArrayList<Card> player = gamePlay.player.hand;
                    Card aceCard = player.get(player.size() - 1);
                    gamePlay.player.hand.set(player.size() - 1, new Card(aceCard.getSuit(), Card.Rank.ACE));
                    gamePlay.player.newAce = false;
                }else if (gamePlay.rectEleven.contains(e.getX(), e.getY()) && gamePlay.player.newAce) {
                    ArrayList<Card> player = gamePlay.player.hand;
                    Card aceCard = player.get(player.size() - 1);
                    gamePlay.player.hand.set(player.size() - 1, new Card(aceCard.getSuit(), Card.Rank.ACE11));
                    gamePlay.player.newAce = false;
                } else if (gamePlay.yesButton.contains(e.getX(), e.getY())) {
                    Card temp = gamePlay.player.hand.get(1);
                    if (temp.getRank().getRankLabel().equals("ace")) {
                        gamePlay.player.splitHand.add(new Card(temp.getSuit(), Card.Rank.ACE11));
                    } else {
                        gamePlay.player.splitHand.add(temp);
                    }
                    gamePlay.player.hand.remove(1);
                    gamePlay.player.setSplitBid(gamePlay.player.getBid());
                    gamePlay.player.split = true;
                    gamePlay.splitGame = true;
                    gamePlay.repaint();
                }
            } else if (gamePlay.splitGame) {
                // Pressing the hold button ends the player's split hand
                if (gamePlay.holdSplitButton.contains(e.getX(), e.getY()) && gamePlay.player.splitHand.size() >= 2 && !gamePlay.player.newAce) {
                    if (gamePlay.splitGame) {
                        gamePlay.splitGame = false;
                    }
                }
                // Pressing the hit button adds a card to the player's hand
                if (gamePlay.hitSplitButton.contains(e.getX(), e.getY()) && !gamePlay.player.newAce) {
                    Card temp = gamePlay.deck.draw();
                    if (temp.getRank().getRankLabel().equals("ace")) {
                        gamePlay.player.newSplitAce = true;
                    }
                    gamePlay.player.splitAdd(temp);
                    // Empty deck is filled and shuffled
                    if (gamePlay.deck.deck.isEmpty()) {
                        gamePlay.deck.deck.clear();
                        gamePlay.deck.fillDeck();
                        gamePlay.deck.shuffle();
                    }
                }
                // A player can double their bet after receiving two cards for their split hand
                if (gamePlay.doubleButton.contains(e.getX(), e.getY())) {
                    if (gamePlay.player.splitHand.size() == 2) {
                        int bidValue = gamePlay.player.getSplitBid();
                        if (bidValue * 2 <= gamePlay.player.getFunds()) {
                            gamePlay.player.setSplitBid(bidValue * 2);
                        }
                    }
                }
                // Pressing the one button updates the ace value to a one and the eleven button updates
                // the ace value to an eleven.
                if (gamePlay.rectOne.contains(e.getX(), e.getY()) && gamePlay.player.newSplitAce) {
                    ArrayList<Card> player = gamePlay.player.splitHand;
                    Card aceCard = player.get(player.size() - 1);
                    gamePlay.player.splitHand.set(player.size() - 1, new Card(aceCard.getSuit(), Card.Rank.ACE));
                    gamePlay.player.newSplitAce = false;
                } else if (gamePlay.rectEleven.contains(e.getX(), e.getY()) && gamePlay.player.newSplitAce) {
                    ArrayList<Card> player = gamePlay.player.splitHand;
                    Card aceCard = player.get(player.size() - 1);
                    gamePlay.player.splitHand.set(player.size() - 1, new Card(aceCard.getSuit(), Card.Rank.ACE11));
                    gamePlay.player.newSplitAce = false;
                }
            } else {
                // A player with a busted/held hand and split hand will end the hand and start a new one
                // with updated funds, bids, and cards.
                if (gamePlay.textButton.contains(e.getX(), e.getY())) {
                    if (gamePlay.player.getFunds() == 0) {
                        gamePlay.player.setFunds(2500);
                    }
                    gamePlay.player.clear();
                    gamePlay.dealer.clear();
                    Card d2Card1 = gamePlay.deck.draw();
                    Card d2Card2 = gamePlay.deck.draw();
                    d2Card2.setFaceUp(false);
                    gamePlay.dealer.add(d2Card1);
                    gamePlay.dealer.add(d2Card2);
                    gamePlay.game = true;
                    gamePlay.bid = false;
                    gamePlay.player.split = false;
                    gamePlay.hitButton.x = 700 / 2 - 100 - 20;
                    gamePlay.holdButton.x = 700 / 2 + 20;
                }
            }
        }
        gamePlay.repaint();
    }
}
