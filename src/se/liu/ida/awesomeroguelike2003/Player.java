package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.List;

class Player extends Entity
{
    private Inventory inventory;
    private InventoryScreenNavigator inventoryScreenNavigator;


    public Player(final int x, final int y, final Game game) {
	    super(x, y, game);
        this.inventory = new Inventory();
        this.inventoryScreenNavigator = new InventoryScreenNavigator();

        this.attackPoints = 10;
        this.defencePoints = 10;
        this.healthPoints = 10;
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
        List<Item> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems();
        if (inventory.getInventoryNavigator() == items.size() - 1) {
            inventory.setInventoryNavigator(0);
        } else {
            inventory.setInventoryNavigator(inventory.getInventoryNavigator() + 1);
        }
    }
    public void decrementInventoryNavigator() {
        List<Item> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems();
        if (inventory.getInventoryNavigator() == 0) {
            inventory.setInventoryNavigator(items.size() - 1);
        } else {
            inventory.setInventoryNavigator(inventory.getInventoryNavigator() - 1);
        }
    }
    public void pickUpSelectedItem() {
        //if there are other objects in the same square as the player and the number of items in player inventory < 110, pick it up
        if(game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems().size() >= 1) {
            if (inventory.getInventorySize() < TestGame.INVENTORYCAPACITY) {
                Item item = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems()
                        .get(inventory.getInventoryNavigator());
                inventory.addToInventory(item);
                game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems()
                        .remove(inventory.getInventoryNavigator());
                inventory.setInventoryNavigator(0);
            }
        }
    }

    public void navigateInvScrUp() {
        int items = inventory.getInventorySize();
        if (inventoryScreenNavigator.getNavigator() == 0) {
            inventoryScreenNavigator.setNavigator(items - 1);
        } else {
            inventoryScreenNavigator.setNavigator(inventoryScreenNavigator.getNavigator() - 1);
        }
    }

    public InventoryScreenNavigator getInventoryScreenNavigator() {
        return inventoryScreenNavigator;
    }

    public void navigateInvScrDown() {
        int items = inventory.getInventorySize();
        if (inventoryScreenNavigator.getNavigator() == items - 1) {
            inventoryScreenNavigator.setNavigator(0);
        } else {
            inventoryScreenNavigator.setNavigator(inventoryScreenNavigator.getNavigator() + 1);
        }
    }

    public void useSelectedItem() {
        //use selected items use method
        if (inventory.getInventorySize() > 0) {
            inventory.getInventory().get(inventoryScreenNavigator.getNavigator()).use();
        }
    }

    @Override
    public void EntityAI() {
        if (healthPoints < 1) {
            game.setGameState(GameState.PLAYERDEAD);
        }
    }
}

