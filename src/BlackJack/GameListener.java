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
                    gamePlay.dealer.getHand().get(1).setFaceUp(true);
                }
                if (gamePlay.hitButton.contains(e.getX(), e.getY()) && !gamePlay.player.newAce) {
                    if (!(gamePlay.player.getBid() == 0)) {
                        gamePlay.bid = false;
                        Card temp = gamePlay.deck.draw();
                        if (temp.getRank().getRankLabel().equals("ace")) {
                            gamePlay.player.newAce = true;
                        }
                        gamePlay.player.add(temp);
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
                if (gamePlay.rectOne.contains(e.getX(), e.getY())) {
                    ArrayList<Card> player = gamePlay.player.hand;
                    Card aceCard = player.get(player.size() - 1);
                    gamePlay.player.hand.set(player.size() - 1, new Card(aceCard.getSuit(), Card.Rank.ACE));
                    gamePlay.player.newAce = false;
                }
                if (gamePlay.rectEleven.contains(e.getX(), e.getY())) {
                    ArrayList<Card> player = gamePlay.player.hand;
                    Card aceCard = player.get(player.size() - 1);
                    gamePlay.player.hand.set(player.size() - 1, new Card(aceCard.getSuit(), Card.Rank.ACE11));
                    gamePlay.player.newAce = false;
                }
                if (gamePlay.bid) {
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
                }
            }
        }
        gamePlay.repaint();
    }
}
