// Sonali Chandra, Alejandro Diaz, and Katie Thien
// CSE 143 Final Project
// The Main class creates the window for the BlackJack game that is played between
// a player and a dealer. The game is played through mouse clicking and standard
// BlackJack rules. A standard deck of card is used including aces. The player is able
// to split their hand and place bets that they can lose, receive a payout from the dealer,
// or push.

package BlackJack;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        Gameplay gamePlay = new Gameplay();
        window.getContentPane().addMouseListener(new GameListener(gamePlay));
        window.setBounds(0, 0, 700, 600);
        window.setTitle("Blackjack");
        ImageIcon a = new ImageIcon("src/img/icon.png");
        window.setIconImage(a.getImage());
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(gamePlay);
    }
}
