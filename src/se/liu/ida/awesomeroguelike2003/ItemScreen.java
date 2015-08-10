package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

final class ItemScreen
{
    private ItemScreen() {}

    public static void draw(Graphics2D g2d) {
	g2d.setColor(new Color(60, 60, 60, 60));
	g2d.fillRect(0, 0, 100, 100);
    }
}
