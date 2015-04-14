package se.liu.ida.awesomeroguelike2003.Items;

import se.liu.ida.awesomeroguelike2003.Item;
import se.liu.ida.awesomeroguelike2003.TestGame;

import java.awt.*;

public class ItemGoldCoin extends Item
{
    @Override public String getName() {
            return name;
        }

    private String name = "Gold Coin";

    @Override public void use() {
    System.out.println("CASH YO");
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
    g.setColor(Color.YELLOW);
    g.fillOval(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
