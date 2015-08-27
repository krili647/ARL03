package se.liu.ida.awesomeroguelike2003;

import java.util.ArrayList;
import java.util.Random;

public class Level
{
    private Random random;
    private int levelNumber;
    private Game game;
    private Map map;

    public Level(final int levelNumber, final Game game){
        this.random = new Random();
	this.levelNumber = levelNumber;
        this.game = game;
        addMap();

        switch (levelNumber){
            case 0:
                addItem(9, 23, new ItemKey(this.game));
                addItem(7, 7, new ItemKey(this.game));
                addItem(7, 7, new ItemGoldCoin(this.game));
                for (int numOfEnemies = 0; numOfEnemies < 1+random.nextInt(3); numOfEnemies++) {
                    addRandomEnemy();
                }
                break;
            case 1:
                addItem(13, 9, new ItemKey(this.game));
                addItem(3, 35, new ItemKey(this.game));
                for (int numOfEnemies = 0; numOfEnemies < 2+random.nextInt(4); numOfEnemies++) {
                    addRandomEnemy();
                }
                break;
            case 2:
                addItem(20, 36, new ItemOrbOfZot(this.game));
                for (int numOfEnemies = 0; numOfEnemies < 4+random.nextInt(4); numOfEnemies++) {
                    addRandomEnemy();
                }
                break;
        }

    }

    public Map getMap() {
        return map;
    }

    private void addMap(){
        this.map = new Map(levelNumber);
    }

    private void addItem(final int x, final int y, Item item){
        this.map.getTileAt(x,y).addToItems(item);
    }

    private void addRandomEnemy() {
        //Add a random enemy on a random location on the map

        int x = random.nextInt(map.getMapWidth());
        int y = random.nextInt(map.getMapHeight());
        Tile tile = map.getTileAt(x,y);
        while (tile.isSolid() || tile.getEntityHere() != null) {
            x = random.nextInt(map.getMapWidth());
            y = random.nextInt(map.getMapHeight());
            tile = map.getTileAt(x,y);
        }
        tile.addToEntities(new Enemy(x, y, game));
    }

}
