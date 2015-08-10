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
        if (isSolid()) {
            g.setColor(new Color(40, 10, 0));
            g.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
        } else {
            g.setColor(new Color(200, 200, 200));
            g.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);

            g.setColor(new Color(40, 10, 0));
            g.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE/4, TestGame.SQUARESIZE);
        }
    }
}
