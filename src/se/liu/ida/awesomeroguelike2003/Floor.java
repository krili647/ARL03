/*
 * An instance of a tile, passable
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class Floor extends Tile
{

    public Floor(){
        super(false, "Floor", 200, 200, 200);
    }

    @Override public void draw(Graphics2D g2d, final int x, final int y) {
	g2d.setColor(new Color(r,g,b));
	g2d.fillRect(x * TestGame.SQUARESIZE, y * TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
