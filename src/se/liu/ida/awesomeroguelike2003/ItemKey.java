package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class ItemKey extends Item
{
    public ItemKey() {
        this.name = "Key";
        this.description = "It's an old rusty key.";
    }

    @Override public void use() {
	System.out.println("NYCKEL YO");
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
	g.setColor(Color.YELLOW);
	g.fillRect(x* TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
