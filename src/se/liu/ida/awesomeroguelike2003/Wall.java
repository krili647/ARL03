/*
 * An instance of a tile, impassable
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class Wall extends Tile
{

    public Wall(){
        super(true, "Wall");
    }

    @Override public void draw(Graphics2D g, final int x, final int y) {
	g.setColor(new Color(70,200,70));
	g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
