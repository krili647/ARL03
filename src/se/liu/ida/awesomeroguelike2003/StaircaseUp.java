package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-07-10.
 */
public class StaircaseUp extends Tile {
    private boolean solid = false;

    private String name = "up";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public void draw(Graphics2D g, final int x, final int y) {
        g.setColor(new Color(200, 200, 200));
        g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
        g.setColor(new Color(0, 0, 0));
        int xPoints[] = {(x)*TestGame.SQUARESIZE, (x+1)*TestGame.SQUARESIZE, (x+1)*TestGame.SQUARESIZE};
        int yPoints[] = {(y+1)*TestGame.SQUARESIZE, (y+1)*TestGame.SQUARESIZE, (y)*TestGame.SQUARESIZE};
        g.fillPolygon(xPoints, yPoints, 3);
        for (Item i : getItems()) {
            i.draw(g, x, y);
        }
        if (getEntityHere() != null) {
            getEntityHere().draw(g, x, y);
        }
    }
}
