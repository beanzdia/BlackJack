package BlackJack;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public final class TextAlignment {

    public void drawCenteredString(Font font, int w, int h, String s, Graphics g) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = 0 + (w - metrics.stringWidth(s)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = 75;
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(s, x, y);
    }

    public void drawCenteredString(Graphics g, String text, Rectangle2D rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        double x = rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
        double y = rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, (int) x, (int) y);
    }

    public void drawCenteredStringOval(Graphics g, String text, Ellipse2D oval, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        double x = oval.getX() + (oval.getWidth() - metrics.stringWidth(text)) / 2;
        double y = oval.getY() + ((oval.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, (int) x, (int) y);
    }

    public void drawCenteredStringTop(Graphics g, String text, Rectangle2D rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        double x = rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
        double y = rect.getY() + metrics.getHeight();
        g.setFont(font);
        g.drawString(text, (int) x, (int) y);
    }
}
