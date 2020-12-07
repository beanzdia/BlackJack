package BlackJack;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay(700,600);
        obj.setBounds(0, 0, 700, 600);
        //obj.setFont(new Font("serif", Font.BOLD, 90));
        obj.setTitle("Blackjack");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}
