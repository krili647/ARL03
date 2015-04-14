package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.List;

public class Player extends Entity
{
    private Inventory inventory;


    public Player(final int x, final int y, final Game game) {
	super(x, y, game);
        this.inventory = new Inventory();
    }

    public Player(final int x, final int y, final Game game, final Inventory inventory) {
        super(x, y, game);
        this.inventory = inventory;
        }

    public Inventory getInventory() {
        return inventory;
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
	g.setColor(new Color(200,0,0));
	g.fillOval(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

    public void pickUp() {
        //öppna itemcomponent
        getGame().setGameState(GameState.PICKINGUP);
        inventory.setInventoryNavigator(0);
    }

    public void incrementInventoryNavigator() {
        List<GameObject> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getGameObjects();
        if (inventory.getInventoryNavigator() == items.size() - 2) {
            inventory.setInventoryNavigator(0);
        } else {
            inventory.setInventoryNavigator(inventory.getInventoryNavigator() + 1);
        }
    }
    public void decrementInventoryNavigator() {
        List<GameObject> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getGameObjects();
        if (inventory.getInventoryNavigator() == 0) {
            inventory.setInventoryNavigator(items.size() - 2);
        } else {
            inventory.setInventoryNavigator(inventory.getInventoryNavigator() - 1);
        }
    }
    public void pickUpSelectedItem() {
        if(game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getGameObjects().size() > 1) {
            GameObject item = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getGameObjects()
                    .get(inventory.getInventoryNavigator());
            inventory.addToInventory(item);
            game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getGameObjects()
                    .remove(inventory.getInventoryNavigator());
            inventory.setInventoryNavigator(0);

            System.out.println(inventory.getInventory());
        }
    }
}
