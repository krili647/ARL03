/*
 * The basic descreet unit of the game. It is an abstract class which can be made real by such instances in the game as walls and floors etc.
 */

package se.liu.ida.awesomeroguelike2003;

import java.util.*;

public abstract class Tile extends GameObject
{
    private boolean isSolid; //Can you walk through the tile?

    protected Tile(final boolean isSolid, final String name, final int r, final int g, final int b){
        super(name, r, g, b);
        this.isSolid = isSolid;
    }

    //Has the tile been discovered yet?
    private boolean seen;

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    //An entity on the tile
    private Entity entityHere = null;

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void addToItems(Item o) {
        items.add(o);
    }

    Entity getEntityHere() {
            return entityHere;
        }

    public void addToEntities(Entity o) {
            this.entityHere = o;
        }

    public void removeFromEntities(Entity o) {
        if (Objects.equals(entityHere, o)) {
            entityHere = null;
        }
    }

    public boolean isSolid() {return this.isSolid;}

}
