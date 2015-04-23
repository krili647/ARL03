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

    private List<Entity> entities = new ArrayList<Entity>();

    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }

    public void addToItems(Item o) {
        items.add(o);
    }

    public List<Entity> getEntities() {
            return entities;
        }

    public void addToEntities(Entity o) {
            entities.add(o);
        }

    public void removeFromEntities(Entity o) {
        if (entities.contains(o)) {
            entities.remove(o);
        } else {
            System.out.println("Nu blidde det fel!"); //ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        }
    }

    public void removeFromItems(Item o) {
        if (items.contains(o)) {
            items.remove(o);
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
