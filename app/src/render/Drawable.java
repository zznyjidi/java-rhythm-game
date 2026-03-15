package render;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface Drawable {
    public void drawElement(Graphics2D graphics2d, Dimension screenSize, long frameTime);

    public boolean isExpired();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
}
