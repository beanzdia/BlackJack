package BlackJack;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameListener extends MouseAdapter {
    public Gameplay gamePlay;

    public GameListener (Gameplay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public void mouseClicked(MouseEvent e) {

        //new Random Card
        ArrayList<Card> d = new ArrayList<Card>();
        for (Card.Suit mySuit : Card.Suit.values()) {
            for (Card.Rank myVal : Card.Rank.values()) {
                if (!myVal.equals(Card.Rank.ACE11)) {
                    d.add(new Card(mySuit, myVal));
                }
            }
        }
        Random r = new Random();
        int n = r.nextInt(d.size());
        Card drawCard = d.get(n);
        //


        if ((e.getButton() == 1) && gamePlay.holdButton.contains(e.getX(), e.getY()) && gamePlay.game && gamePlay.player.hand.size() >= 2 && !gamePlay.player.newAce) {
            gamePlay.game = false;
            gamePlay.dealer.getHand().get(1).setFaceUp(true);
            gamePlay.repaint();
        }
        // red is hold
        if ((e.getButton() == 1) && gamePlay.hitButton.contains(e.getX(), e.getY()) && gamePlay.game && !gamePlay.player.newAce) {
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
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.textButton.contains(e.getX(), e.getY()) && !gamePlay.game) {
            gamePlay.player.clear();
            gamePlay.dealer.clear();
            Card d2Card1 = gamePlay.deck.draw();
            Card d2Card2 = gamePlay.deck.draw();
            d2Card2.setFaceUp(false);
            gamePlay.dealer.add(d2Card1);
            gamePlay.dealer.add(d2Card2);
            gamePlay.game = true;
            gamePlay.repaint();
        }

        // if 5 oval
        if ((e.getButton() == 1) && gamePlay.thousandsButton.contains(e.getX(), e.getY())) {
            gamePlay.player.setBid(gamePlay.player.getBid() + 1000);
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.hundredsButton.contains(e.getX(), e.getY())) {
            gamePlay.player.setBid(gamePlay.player.getBid() + 100);
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.tensButton.contains(e.getX(), e.getY())) {
            gamePlay.player.setBid(gamePlay.player.getBid() + 10);
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.onesButton.contains(e.getX(), e.getY())) {
            gamePlay.player.setBid(gamePlay.player.getBid() + 1);
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.rectOne.contains(e.getX(), e.getY())) {
            Card ace = gamePlay.player.hand.get(gamePlay.player.hand.size() - 1);
            gamePlay.player.hand.set(gamePlay.player.hand.size() - 1, new Card(ace.getSuit(), Card.Rank.ACE1));
            gamePlay.player.newAce = false;
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.rectEleven.contains(e.getX(), e.getY())) {
            Card ace = gamePlay.player.hand.get(gamePlay.player.hand.size() - 1);
            gamePlay.player.hand.set(gamePlay.player.hand.size() - 1, new Card(ace.getSuit(), Card.Rank.ACE11));
            gamePlay.player.newAce = false;
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.yesButton.contains(e.getX(), e.getY())) {
            gamePlay.player.hand.set(gamePlay.player.hand.size() - 1, drawCard);
            //gamePlay.player.splitHand.set(gamePlay.player.hand.size() - 1, drawCard);
            gamePlay.repaint();
        }
        if ((e.getButton() == 1) && gamePlay.noButton.contains(e.getX(), e.getY())) {
            Card noSplit1 = gamePlay.player.hand.get(gamePlay.player.hand.size() - 2);
            Card noSplit2 = gamePlay.player.hand.get(gamePlay.player.hand.size() - 1);

            gamePlay.player.hand.set(gamePlay.player.hand.size() -1, new Card(noSplit1.getSuit(), Card.Rank.SEVEN));
            gamePlay.player.hand.set(gamePlay.player.hand.size() -2, new Card(noSplit2.getSuit(), Card.Rank.SEVEN));
            gamePlay.repaint();
        }
    }
}
