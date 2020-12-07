package BlackJack;

import javax.swing.*;
import java.awt.*;

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
