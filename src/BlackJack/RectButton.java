package BlackJack;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectButton extends Rectangle2D.Double {
    private String text;
    TextAlignment textAlign = new TextAlignment();

    RectButton(int x, int y, int width, int height, String text) {
        super(x,y,width,height);
        this.text = text;
    }

    public void draw(Graphics g, Color color, Font font) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(this);
        g2d.setColor(Color.black);
        textAlign.drawCenteredString(g, this.text, this, font);
    }

    public String getText() {
        return this.text;
    }

}
