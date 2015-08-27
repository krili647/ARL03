/*
 * An instance of a tile, impassable
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class Wall extends Tile
{

    public Wall(){
        super(true, "Wall", 70, 200, 70);
    }

    @Override public void draw(Graphics2D g2d, final int x, final int y) {
	g2d.setColor(new Color(r,g,b));
	g2d.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
