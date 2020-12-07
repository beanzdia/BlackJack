package BlackJack;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class OvalButton extends Ellipse2D.Double {

    private String text;
    TextAlignment textAlign = new TextAlignment();

    OvalButton(int x, int y, int width, int height, String text) {
        super(x,y,width,height);
        this.text = text;
    }

    OvalButton(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void draw(Graphics g, Color color, Font font) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(this);
        g2d.setColor(Color.black);
        textAlign.drawCenteredStringOval(g, this.text, this, font);
    }

    public String getText() {
        return this.text;
    }
}
