package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-04-23.
 */
public class Empty extends Tile {

    @Override public boolean isSolid() {
        return solid;
    }

    private boolean solid = true;

    private String name = "empty";

    @Override public void draw(Graphics2D g, final int x, final int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
