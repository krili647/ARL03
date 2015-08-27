package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.Collection;

public class ItemGoldCoin extends Item
{


    public ItemGoldCoin(final Game game) {
        super("Gold Coin", "It's an old coin", 255, 255, 0, game);

    }

    @Override public void use() {
    System.out.println("CASH YO");
    }

    @Override public void draw(final Graphics2D g2d, final int x, final int y) {
    g2d.setColor(new Color(r,g,b));
    g2d.fillOval(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

}
