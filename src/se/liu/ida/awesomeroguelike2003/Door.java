package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-07-11.
 */

public class Door extends Tile {

    public Door(){
        super(true, "Door", 40, 10, 0);
    }

    @Override
    public void draw(Graphics2D g2d, final int x, final int y) {
        g2d.setColor(new Color(r,g,b));
        g2d.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
