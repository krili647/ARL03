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
    private final Tile[][] map;
    private int staircaseUpX;
    private int staircaseUpY;
    private final int staircaseDownX;
    private final int staircaseDownY;

    public int getStaircaseDownX() {
        return staircaseDownX;
    }

    public int getStaircaseDownY() {
        return staircaseDownY;
    }

    public int getMapWidth(){
	return map.length;
    }
    public int getMapHeight() {
	return map[0].length;
    }

    public int getStaircaseUpX() {
        return staircaseUpX;
    }

    public int getStaircaseUpY() { return staircaseUpY; }

    public Tile getTileAt(final int x, final int y) {
	return map[x][y];
    }

    public Map(final String name) {

        //Maps consists of two dimensional arrays of numbers
        //It reads the numbers and translates them to different tile types
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
        this.staircaseUpX = numbers.get(2);
        this.staircaseUpY = numbers.get(3);
        this.staircaseDownX = numbers.get(4);
        this.staircaseDownY = numbers.get(5);


        for(int x = 0; x < numbers.size() - 6; x++) {
            int deltaX = (x)% numbers.get(0);
            int deltaY = (x)/ numbers.get(1);
            int tileNumber = numbers.get(x + 6);

            //Translate from number to tile type
            switch(tileNumber) {
                case 0: setTileAt(deltaX, deltaY, new Empty()); break;
                case 1: setTileAt(deltaX, deltaY, new Wall()); break;
                case 2: setTileAt(deltaX, deltaY, new Floor()); break;
                case 3: setTileAt(deltaX, deltaY, new StaircaseUp()); break;
                case 4: setTileAt(deltaX, deltaY, new StaircaseDown()); break;
                case 5: setTileAt(deltaX, deltaY, new Door()); break;

            }

        }
    }
    void setTileAt(final int x, final int y, Tile tile)
    {
	map[x][y] = tile;
    }

}
