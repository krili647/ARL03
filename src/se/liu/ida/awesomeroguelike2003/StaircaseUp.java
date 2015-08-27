package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

/**
 * Created by leopold on 2015-07-10.
 */
public class StaircaseUp extends Tile {

    public StaircaseUp(){

        super(false, "StaircaseUp", 200, 200, 200);
    }

    @Override public void draw(Graphics2D g2d, final int x, final int y) {
        g2d.setColor(new Color(r,g,b));
        g2d.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
        g2d.setColor(new Color(0, 0, 0));
        int[] xPoints = { (x) * TestGame.SQUARESIZE, (x + 1) * TestGame.SQUARESIZE, (x + 1) * TestGame.SQUARESIZE };
        int[] yPoints = { (y + 1) * TestGame.SQUARESIZE, (y + 1) * TestGame.SQUARESIZE, (y) * TestGame.SQUARESIZE };
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}
