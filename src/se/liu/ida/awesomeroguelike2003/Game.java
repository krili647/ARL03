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
    private RLFrame frame;
    private MessageRoll messageRoll;
    private List<Level> levels;
    private static final int NUMOFLEVELS = 3; //This is a fixed number

    public RLFrame getFrame() {
        return frame;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public RLComponent getPaintComponent() {
        return paintComponent;
    }

    private final RLComponent paintComponent;

    public Map getMap() {
        return map;
    }

    Player getPlayer() {
        return player;
    }

    public Level getLevel(final int levelNumber) { return levels.get(levelNumber); }

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

        traverseMap(playerX, playerY, visibility);

    }

    private void traverseMap(final int x, final int y, final int n) {
        //If a tile is discovered, make it visible on the map

        for (int dX = -1; dX < 2; dX++) {
            for (int dY = -1; dY < 2; dY++) {
                if (x + dX > 0 && x + dX < getLevel(levelNumber).getMap().getMapWidth()) {
                    if (y + dY > 0 && y + dY < getLevel(levelNumber).getMap().getMapHeight()) {
                        if (!getLevel(levelNumber).getMap().getTileAt(x + dX, y + dY).isSolid()) {
                            getLevel(levelNumber).getMap().getTileAt(x + dX, y + dY).setSeen(true);
                            if (n - 1 > 0) {
                                traverseMap(x + dX, y + dY, n - 1);
                            }
                        } else {
                            getLevel(levelNumber).getMap().getTileAt(x + dX, y + dY).setSeen(true);
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

    public MessageRoll getMessageRoll(){
        return messageRoll;
    }

    public Game() {
        this.gameState = GameState.PLAYING;
        this.messageRoll = new MessageRoll();
        this.levels = new ArrayList<>();

        loadLevels();
        this.levelNumber = 0;
        this.map = getLevel(levelNumber).getMap();

        this.player = new Player(map.getStaircaseUpX(), map.getStaircaseUpY(), this);

        messageRoll.addMessage("Welcome to The Dungeon of Zot!");
        messageRoll.addMessage("If you can leave this place with The Orb of Zot " +
                               "uncountable riches and glory will be yours! " +
                               "Although many have tried before you, none have secceeded...");
        messageRoll.addMessage("");
        messageRoll.addMessage("[enter] to use stairs | \",\" to pick up items | \"i\" for inventory | [arrow keys/numPad] to move");


        this.paintComponent = new RLComponent(this);
        this.frame = new RLFrame(this);

        updateMap();

    }



    public void changeLevel(final int newLevelNumber){

        if (newLevelNumber > -1 && newLevelNumber < NUMOFLEVELS) {
            //remove player from the previous level
            getLevel(levelNumber).getMap().getTileAt(player.getX(), player.getY())
                    .removeFromEntities(player);

            Level nextLevel = getLevel(newLevelNumber);
            this.map = getLevel(newLevelNumber).getMap();

            int startLocationX;
            int startLocationY;

            if (newLevelNumber < levelNumber){
                startLocationX = nextLevel.getMap().getStaircaseDownX();
                startLocationY = nextLevel.getMap().getStaircaseDownY();
            } else {
                startLocationX = nextLevel.getMap().getStaircaseUpX();
                startLocationY = nextLevel.getMap().getStaircaseUpY();
            }

            player.setX(startLocationX);
            player.setY(startLocationY);
            nextLevel.getMap().getTileAt(startLocationX, startLocationY).addToEntities(player);

            levelNumber = newLevelNumber;

        } else {
            System.out.println("NEJ!");
        }
    }

    public void loadLevels() {
        //Create levels, put enemies and items on every level
        for (int levelNumber = 0; levelNumber < NUMOFLEVELS; levelNumber++){
            this.levels.add(levelNumber, new Level(levelNumber, this));
        }

    }

    private void updateAI() {
        for (int x = 0; x < getLevel(levelNumber).getMap().getMapWidth(); x++) {
            for (int y = 0; y < getLevel(levelNumber).getMap().getMapHeight(); y++) {
                if (getLevel(levelNumber).getMap().getTileAt(x, y).getEntityHere() != null) {
                    getLevel(levelNumber).getMap().getTileAt(x, y).getEntityHere().EntityAI();
                }
            }
        }
    }
}
