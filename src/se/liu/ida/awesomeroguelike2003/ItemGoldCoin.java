package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class ItemGoldCoin extends Item
{

    public ItemGoldCoin(final Game game) {
        super("Gold Coin", "It's an old coin", game);
    }

    @Override public void use() {
    System.out.println("CASH YO");
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
    g.setColor(Color.YELLOW);
    g.fillOval(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

}
