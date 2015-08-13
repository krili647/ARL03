/*
 * The basic descreet unit of the game. It is an abstract class which can be made real by such instances in the game as walls and floors etc.
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Tile implements Drawable
{
    private boolean isSolid; //Can you walk through the tile?
    private String name;

    public Tile(final boolean isSolid, final String name){
        this.isSolid = isSolid;
        this.name = name;
    }

    //Has the tile been discovered yet?
    private boolean seen;

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getName() { return name; }

    //An entity on the tile
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
            System.out.println("Nu blidde det fel! (E)" + o); //ERROR
        }
    }

    public void removeFromItems(Item o) {
        if (items.contains(o)) {
            items.remove(o);
        } else {
            System.out.println("Nu blidde det fel!" + o); //ERROR
        }
    }

    public void setSolid(Boolean bool) {
        this.isSolid = bool;
    }

    public boolean isSolid() {return this.isSolid;}

    public void draw(Graphics2D g, final int x, final int y) {
	g.setColor(new Color(0,0,0));
	g.fillRect(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

}
