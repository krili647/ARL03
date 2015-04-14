package se.liu.ida.awesomeroguelike2003.Items;

import se.liu.ida.awesomeroguelike2003.Item;
import se.liu.ida.awesomeroguelike2003.TestGame;

import java.awt.*;

public class ItemKey extends Item
{
    @Override public String getName() {
        return name;
    }

    private String name = "key";

    @Override public void use() {
	System.out.println("NYCKEL YO");
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
	g.setColor(Color.YELLOW);
	g.fillRect(x* TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
