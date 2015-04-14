package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class ItemScreen
{
    private Game game;

    public ItemScreen(final Game game) {
	this.game = game;
    }

    public static void draw(Graphics2D g2d, final int x, final int y) {
	g2d.setColor(new Color(60, 60, 60, 60));
	g2d.fillRect(0, 0, 100, 100);
    }
}
