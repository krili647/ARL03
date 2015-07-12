package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-07-11.
 */

public class ItemOrbOfZot extends Item {

    public ItemOrbOfZot(final Game game) {
        super(game);
        this.description = "The Magical Orb of Zot!";
        this.name = "Orb of Zot";
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
        g.setColor(new Color(200,100,100));
        g.fillOval(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

    @Override public void use() {}
}
