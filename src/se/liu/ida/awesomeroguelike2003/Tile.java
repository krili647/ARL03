/*
 * The basic descreet unit of the game. It is an abstract class which can be made real by such instances in the game as walls and floors etc.
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Tile implements Drawable
{
    private boolean solid;

    public List<GameObject> gameObjects = new ArrayList<GameObject>();

    public List<GameObject> getGameObjects() {
            return gameObjects;
        }

    public void addToGameObjects(GameObject o) {
            gameObjects.add(o);
        }

    public void removeFromGameObjects(GameObject o) {
        if (gameObjects.contains(o)) {
            gameObjects.remove(o);
        } else {
            System.out.println("Nu blidde det fel!"); //ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        }
    }

    boolean isSolid() {
	return this.solid;
    }

    public void draw(Graphics2D g, final int x, final int y) {
	g.setColor(new Color(0,0,0));
	g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

}
