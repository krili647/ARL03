package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.awt.print.Book;

/**
 * Created by leopold on 2015-07-11.
 */
public class Door extends Tile {

    private boolean solid = true;

    private String name = "door";

    public String getName() {
        return name;
    }

    @Override public boolean isSolid() {
        return solid;
    }
    @Override public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public void setName(String name) {
        this.name = name;
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
