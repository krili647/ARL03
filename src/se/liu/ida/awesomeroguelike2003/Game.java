/**
 * Starts the program: contains the main method
 */

package se.liu.ida.awesomeroguelike2003;

import java.util.ArrayList;
import java.util.List;

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
        //boolean running = true;
        this.gameState = GameState.PLAYING;
        this.messageRoll = new ArrayList<String>();


        loadLevels();
        this.map = levels.get(0);
        this.levelNumber = 0;
        this.player = new Player(map.getStaircaseUpX(), map.getStaircaseUpY(), this);

        new Enemy(30,6,this);
        map.getTileAt(7,7).addToItems(new ItemKey(this));
        map.getTileAt(7,7).addToItems(new ItemKey(this));
        map.getTileAt(9,7).addToItems(new ItemOrbOfZot(this));
        addToMessageRoll("              Welcome to The Dungeon of Zot!");
        addToMessageRoll("              If you can leave this place with The Orb of Zot");
        addToMessageRoll("              uncountable riches and glory will be yours!");
        addToMessageRoll("              Although many have tried before you, none have succeeded...");
        addToMessageRoll("[enter] to use stairs | \",\" to pick up items | \"i\" for inventory | [arrow keys/numPad] to move");

        this.paintComponent = new RLComponent(this);
        this.RLFrame = new RLFrame(this);

    }

    public void addLevel(Map map) {
        this.levels.add(map);
    }

    private void loadLevels() {
        this.levels.add(new Map("Map1"));
        this.levels.add(new Map("Map2"));
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
