/*
 * A map object is one level of the game, it containt an 2Darray of tiles
 * and methods for setting/getting them as well as other basic info about the level
 */

package se.liu.ida.awesomeroguelike2003;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map
{
    private Tile[][] map;
    private int playerStartX;
    private int playerStartY;

    public int getMapWidth(){
	return map.length;
    }
    public int getMapHeight() {
	return map[0].length;
    }

    public int getPlayerStartX() {
        return playerStartX;
    }

    public void setPlayerStartX(int playerStartX) {
        this.playerStartX = playerStartX;
    }

    public int getPlayerStartY() {
        return playerStartY;
    }

    public void setPlayerStartY(int playerStartY) {
        this.playerStartY = playerStartY;
    }

    public Tile getTileAt(final int x, final int y) {
	return map[x][y];
    }

    public Map(final int width, final int height) {
	this.map = new Tile[width][height];
	createMap(getMapWidth(), getMapHeight());
    }

    public Map(final String name) {
        List<Integer> numbers = new ArrayList<Integer>();
        try {
            for (String line : Files.readAllLines(Paths.get("src/se/liu/ida/awesomeroguelike2003/maps/" + name), Charset.defaultCharset())) {
                for (String part : line.split("\\s+")) {
                    Integer i = Integer.valueOf(part);
                    numbers.add(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map = new Tile[numbers.get(0)][numbers.get(1)];


        for(int x = 0; x < numbers.size() - 2; x++) {
            int deltaX = (x)% numbers.get(0);
            int deltaY = (x)/ numbers.get(1);
            int tileNumber = numbers.get(x + 2);


            System.out.println(x + ":" + numbers.size() + ":" + deltaX + " " + deltaY + " " + tileNumber);

            switch(tileNumber) {
                case 0: setTileAt(deltaX, deltaY, new Empty()); break;
                case 1: setTileAt(deltaX, deltaY, new Wall()); break;
                case 2: setTileAt(deltaX, deltaY, new Floor()); break;
            }

        }
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
