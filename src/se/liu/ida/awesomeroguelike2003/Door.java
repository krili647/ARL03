package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-07-11.
 */

public class Door extends Tile {

    public Door(){
        super(true, "Door");

    }

    @Override
    public void draw(Graphics2D g, final int x, final int y) {
        g.setColor(new Color(40, 10, 0));
        g.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
