/**
 * Starts the program: contains the main method
 */

package se.liu.ida.awesomeroguelike2003;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private boolean running;
    private Map map;
    private Player player;
    private GameState gameState;
    private int levelNumber;
    private RLFrame RLFrame;

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

    private RLComponent paintComponent;

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
        }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(final GameState gameState) {
        this.gameState = gameState;
    }

    public Game() {
        this.running = true;
        this.gameState = GameState.PLAYING;

        loadLevels();
        this.map = levels.get(0);
        this.levelNumber = 0;
        this.player = new Player(levels.get(0).getStaircaseUpX(),levels.get(0).getStaircaseUpY(), this);

        map.getTileAt(7,7).addToItems(new ItemKey(this));
        map.getTileAt(9,7).addToItems(new ItemOrbOfZot(this));

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
}
