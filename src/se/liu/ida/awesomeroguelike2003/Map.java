/*
 * A map object is one level of the game, it containt an 2Darray of tiles
 * and methods for setting/getting them as well as other basic info about the level
 */

package se.liu.ida.awesomeroguelike2003;

import java.util.Random;

public class Map
{
    private Tile[][] map;

    public int getMapWidth(){
	return map.length;
    }
    public int getMapHeight() {
	return map[0].length;
    }

    public Tile getTileAt(final int x, final int y) {
	return map[x][y];
    }

    public Map(final int width, final int height) {
	this.map = new Tile[width][height];
	createMap(getMapWidth(), getMapHeight());
    }

    public void setTileAt(final int x, final int y, Tile tile)
    {
	map[x][y] = tile;
    }
    public void createRandomMap() {
	Random rnd = new Random();
	for(int x = 0; x < getMapWidth(); x++) {
	    for(int y = 0; y < getMapHeight(); y++) {
		if (rnd.nextBoolean()) {
		    setTileAt(x, y, new Wall());
		} else {
		    setTileAt(x, y, new Floor());
		}
	    }
	}
    }

    public void createMap(int x, int y){
	for (x = 0; x < getMapWidth(); x++){
	    for (y = 0; y < getMapHeight(); y++){
		if (y == 0 || y == getMapHeight() -1){
		    setTileAt(x, y, new Wall());
		} else if(x == 0 || x == getMapWidth() - 1) {
		    setTileAt(x, y, new Wall());
		} else {
		    setTileAt(x, y, new Floor());
		}
	    }
	}
    }
}
