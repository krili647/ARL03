/**
 * Starts the program: contains the main method
 */

package se.liu.ida.awesomeroguelike2003;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private Map map;
    private Player player;
    private GameState gameState;
    private int levelNumber;
    private RLFrame RLFrame;
    private List<String> messageRoll;

    public RLFrame getRLFrame() {
        return RLFrame;
    }

    public void setRLFrame(RLFrame RLFrame) {
        this.RLFrame = RLFrame;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public RLComponent getPaintComponent() {
        return paintComponent;
    }

    private final RLComponent paintComponent;

    public Map getMap() {
        return map;
    }

    public void setMap(final Map map) {
        this.map = map;
    }

    private List<Map> levels = new ArrayList<Map>();

    public Player getPlayer() {
        return player;
    }

    public List<Map> getLevels() {
        return levels;
    }

    public void setLevels(List<Map> levels) {
        this.levels = levels;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public void gameUpdated() {
        	paintComponent.repaint();
            //update all AI
            updateAI();
            updateMap();
        }

    private void updateMap() {
        final int visibility = 3;

        final int playerX = player.getX();
        final int playerY = player.getY();

        traverseMap(playerX,playerY, visibility);



    }

    private void traverseMap(final int x, final int y, final int n) {
        //If a tile is discovered, make it visible on the map

        for (int dX = -1; dX < 2; dX++) {
            for (int dY = -1; dY < 2; dY++) {
                if (x + dX > 0 && x + dX < map.getMapWidth()) {
                    if (y + dY > 0 && y + dY < map.getMapHeight()) {
                        if (!map.getTileAt(x + dX, y + dY).isSolid()) {
                            map.getTileAt(x + dX, y + dY).setSeen(true);
                            if (n - 1 > 0) {
                                traverseMap(x + dX, y + dY, n - 1);
                            }
                        } else {
                            map.getTileAt(x + dX, y + dY).setSeen(true);
                        }
                    }
                }
            }
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(final GameState gameState) {
        this.gameState = gameState;
    }

    public List<String> getMessageRoll() {
        return messageRoll;
    }

    public void addToMessageRoll(String message) {
        messageRoll.add(message);
    }

    public Game() {
        this.gameState = GameState.PLAYING;
        this.messageRoll = new ArrayList<String>();


        loadLevels();
        this.map = levels.get(0);
        this.levelNumber = 0;
        this.player = new Player(map.getStaircaseUpX(), map.getStaircaseUpY(), this);

        addToMessageRoll("              Welcome to The Dungeon of Zot!");
        addToMessageRoll("              If you can leave this place with The Orb of Zot");
        addToMessageRoll("              uncountable riches and glory will be yours!");
        addToMessageRoll("              Although many have tried before you, none have succeeded...");
        addToMessageRoll("[enter] to use stairs | \",\" to pick up items | \"i\" for inventory | [arrow keys/numPad] to move");

        this.paintComponent = new RLComponent(this);
        this.RLFrame = new RLFrame(this);

        updateMap();

    }

    private void loadLevels() {
        //Create levels, put enemies and items on every level
        Random rnd = new Random();

        this.levels.add(new Map("Map1"));
        setMap(levels.get(0));

        for (int n = 0; n < rnd.nextInt(3)+1; n++) {
            addRandomEnemy(levels.get(0));
        }

        new Enemy(30,6,this);
        map.getTileAt(9,23).addToItems(new ItemKey(this));
        map.getTileAt(7,7).addToItems(new ItemKey(this));


        this.levels.add(new Map("Map2"));
        setMap(levels.get(1));

        for (int n = 0; n < rnd.nextInt(4)+2; n++) {
            addRandomEnemy(levels.get(1));
        }
        map.getTileAt(13,9).addToItems(new ItemKey(this));
        map.getTileAt(3,35).addToItems(new ItemKey(this));

        this.levels.add(new Map("Map3"));
        setMap(levels.get(2));

        map.getTileAt(20,36).addToItems(new ItemOrbOfZot(this));
        for (int n = 0; n < rnd.nextInt(4)+4; n++) {
            addRandomEnemy(levels.get(2));
        }
    }

    private void addRandomEnemy(Map map) {
        //Add a random enemy on a random location on the map
        Random rnd = new Random();

        int x = rnd.nextInt(map.getMapWidth());
        int y = rnd.nextInt(map.getMapHeight());
        Tile tile = map.getTileAt(x,y);
        while (tile.isSolid() || tile.getEntityHere() != null) {
            x = rnd.nextInt(map.getMapWidth());
            y = rnd.nextInt(map.getMapHeight());
            tile = map.getTileAt(x,y);
        }
        tile.addToEntities(new Enemy(x, y, this));
    }

    private void updateAI() {
        for (int x = 0; x < map.getMapWidth(); x++) {
            for (int y = 0; y < map.getMapHeight(); y++) {
                if (map.getTileAt(x, y).getEntityHere() != null) {
                    map.getTileAt(x, y).getEntityHere().EntityAI();
                }
            }
        }
    }
}
