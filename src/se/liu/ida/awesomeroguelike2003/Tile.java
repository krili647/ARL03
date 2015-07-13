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

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Entity entityHere;

    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }

    public void addToItems(Item o) {
        items.add(o);
    }

    public Entity getEntityHere() {
            return entityHere;
        }

    public void addToEntities(Entity o) {
            this.entityHere = o;
        }

    public void removeFromEntities(Entity o) {
        if (entityHere == o) {
            entityHere = null;
        } else {
            System.out.println("Nu blidde det fel!" + o); //ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        }
    }

    public void removeFromItems(Item o) {
        if (items.contains(o)) {
            items.remove(o);
        } else {
            System.out.println("Nu blidde det fel!" + o); //ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        }
    }

    public void setSolid(Boolean bool) {
        this.solid = bool;
    }

    public boolean isSolid() {return this.solid;}

    public void draw(Graphics2D g, final int x, final int y) {
	g.setColor(new Color(0,0,0));
	g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

}
