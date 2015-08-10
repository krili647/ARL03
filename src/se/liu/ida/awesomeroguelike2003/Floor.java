/*
 * An instance of a tile, passable
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class Floor extends Tile
{
    public Floor(){
        super(false, "Floor");
    }

    @Override public void draw(Graphics2D g, final int x, final int y) {
	g.setColor(new Color(200, 200, 200));
	g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
