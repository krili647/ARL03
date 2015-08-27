package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-07-11.
 */

public class ItemOrbOfZot extends Item {

    public ItemOrbOfZot(final Game game) {
        super("Orb of Zot", "The Magical Orb of Zot!", 200, 100, 100, game);

    }

    @Override public void draw(final Graphics2D g2d, final int x, final int y) {
        g2d.setColor(new Color(r,g,b));
        g2d.fillOval(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

    @Override public void use() {}
}
