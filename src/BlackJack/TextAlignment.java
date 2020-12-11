// The TextAlignment class aligns the text in the center of rectangle or oval shapes.

package BlackJack;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public final class TextAlignment {

    // post: Draws a message centered in the middle of a given rectangle shape from a given rectangle
    //       shape and font. Text represent the message on the shape, rect is the rectangle shape,
    //       and font represents the desired font for the text.
    public void drawCenteredString(Graphics g, String text, Rectangle2D rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        double x = rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
        double y = rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, (int) x, (int) y);
    }

    // post: Draws a message centered in teh middle of a given oval shape from a given oval
    //       shape and font. Text represent the message on the shape, oval is the oval shape,
    //       and font represents the desired font for the text.
    public void drawCenteredStringOval(Graphics g, String text, Ellipse2D oval, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        double x = oval.getX() + (oval.getWidth() - metrics.stringWidth(text)) / 2;
        double y = oval.getY() + ((oval.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, (int) x, (int) y);
    }

    // post: Draws a message centered to the top on a given rectangle shape from a given rectangle
    //       shape and font. Text represent the message on the shape, rect is the rectangle shape,
    //       and font represents the desired font for the text.
    public void drawCenteredStringTop(Graphics g, String text, Rectangle2D rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        double x = rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
        double y = rect.getY() + metrics.getHeight();
        g.setFont(font);
        g.drawString(text, (int) x, (int) y);
    }
}
