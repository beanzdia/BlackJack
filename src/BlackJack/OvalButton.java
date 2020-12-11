// The OvalButton class represents oval button for the BlackJack game
// the player is able to interact with. The buttons can be drawn with a title.

package BlackJack;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class OvalButton extends Ellipse2D.Double {
    // text stores the message on the button
    private String text;
    // textAlign generates an align text graphic
    private TextAlignment textAlign;

    // post: Constructs an oval button from a given position (x,y) pair,
    //       dimensions (width,height), and text. The position (x,y) pair represents
    //       the buttons upper-left corner's position, the dimensions (width, height)
    //       determine the geometry of the shape, and the text is a title for the button.
    OvalButton(int x, int y, int width, int height, String text) {
        super(x,y,width,height);
        this.text = text;
        textAlign = new TextAlignment();
    }

    // post: Draws the oval button with centered text. The parameter g generates
    //       the shapes, color represents the buttons color, and font represents the
    //       text's font.
    public void draw(Graphics g, Color color, Font font) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(this);
        g2d.setColor(Color.black);
        textAlign.drawCenteredStringOval(g, this.text, this, font);
    }

    // post: Returns the oval button's title text.
    public String getText() {
        return this.text;
    }
}
