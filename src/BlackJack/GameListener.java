package BlackJack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameListener extends MouseAdapter {
    public Gameplay gamePlay;

    public GameListener (Gameplay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            if (gamePlay.game) {
                if (gamePlay.holdButton.contains(e.getX(), e.getY()) && gamePlay.player.hand.size() >= 2 && !gamePlay.player.newAce) {
                    gamePlay.game = false;
                }
                if (gamePlay.hitButton.contains(e.getX(), e.getY()) && !gamePlay.player.newAce && gamePlay.bid) {
                    if (gamePlay.bid) {
                        Card temp = gamePlay.deck.draw();
                        if (temp.getRank().getRankLabel().equals("ace")) {
                            gamePlay.player.newAce = true;
                        }
                        gamePlay.player.add(temp);
                        // gamePlay.player.add(gamePlay.test.draw());
                        if (gamePlay.deck.deck.isEmpty()) {
                            gamePlay.deck.deck.clear();
                            gamePlay.deck.fillDeck();
                            gamePlay.deck.shuffle();
                        }
                    }
                }
                if (gamePlay.doubleButton.contains(e.getX(), e.getY())) {
                    if (gamePlay.player.getHand().size() == 2) {
                        int bidValue = gamePlay.player.getBid();
                        if (bidValue * 2 <= gamePlay.player.getFunds()) {
                            gamePlay.player.setBid(bidValue * 2);
                        }
                    }
                }

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
                if (gamePlay.bidButton.contains(e.getX(), e.getY())) {
                    gamePlay.bid = true;
                    gamePlay.repaint();
                }
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
                    gamePlay.player.splitHand.add(gamePlay.player.hand.get(1));
                    gamePlay.player.hand.remove(1);
                    gamePlay.player.setSplitBid(gamePlay.player.getBid());
                    gamePlay.player.split = true;
                    gamePlay.splitGame = true;
                    gamePlay.repaint();
                }
                /*
                else if (gamePlay.noButton.contains(e.getX(), e.getY())) {
                    gamePlay.player.split = false;
                    gamePlay.splitGame = false;
                    gamePlay.repaint();
                }

                 */

            } else if (gamePlay.splitGame) {
                if (gamePlay.holdSplitButton.contains(e.getX(), e.getY()) && gamePlay.player.splitHand.size() >= 2 && !gamePlay.player.newAce) {
                    if (gamePlay.splitGame) {
                        gamePlay.splitGame = false;
                        //gamePlay.dealer.getHand().get(1).setFaceUp(true);
                    }
                }
                if (gamePlay.hitSplitButton.contains(e.getX(), e.getY()) && !gamePlay.player.newAce) {
                    gamePlay.splitBid = false;
                    //Card temp = gamePlay.deck.draw();
                    //if (temp.getRank().getRankLabel().equals("ace")) {
                    //    gamePlay.player.newAce = true;
                    //}
                    // gamePlay.player.add(temp);
                    gamePlay.player.splitAdd(gamePlay.deck.draw());
                    if (gamePlay.deck.deck.isEmpty()) {
                        gamePlay.deck.deck.clear();
                        gamePlay.deck.fillDeck();
                        gamePlay.deck.shuffle();
                    }
                }
                if (gamePlay.doubleButton.contains(e.getX(), e.getY())) {
                    if (gamePlay.player.getSplitHand().size() == 2) {
                        int bidValue = gamePlay.player.getSplitBid();
                        if (bidValue * 2 <= gamePlay.player.getFunds()) {
                            gamePlay.player.setSplitBid(bidValue * 2);
                        }
                    }
                }
            } else {
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
