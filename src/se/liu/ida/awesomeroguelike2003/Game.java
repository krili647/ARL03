/**
 * Starts the program: contains the main method
 */

package se.liu.ida.awesomeroguelike2003;

import se.liu.ida.awesomeroguelike2003.Items.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    private boolean running = true;
    private Map map;
    private Player player;
    private GameState gameState;

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

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public void gameUpdated() {
        	paintComponent.repaint();
        	System.out.println(player.getX());
        }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(final GameState gameState) {
        this.gameState = gameState;
    }

    public Game() {
        this.gameState = GameState.PLAYING;
        map = new Map(40, 40);
        addLevel(map);
        this.player = new Player(2,2, this);
        map.getTileAt(5, 5).addToItems(new ItemGoldCoin());
        map.getTileAt(5, 5).addToItems(new ItemGoldCoin());
        map.getTileAt(5, 5).addToItems(new ItemGoldCoin());
        map.getTileAt(5, 5).addToItems(new ItemKey());
        map.getTileAt(2, 2).addToEntities(player);
        this.paintComponent = new RLComponent(this);
        RLFrame frame = new RLFrame(this);



    }

    public void addLevel(Map map) {
        this.levels.add(map);
    }
}
