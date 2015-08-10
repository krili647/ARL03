package se.liu.ida.awesomeroguelike2003;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    public Inventory() {}

    public Inventory(List<Item> items) {
	this.inventory = items;
    }

    private List<Item> inventory = new ArrayList<Item>();

    private int inventoryNavigator = 0;

    public int getInventoryNavigator() {
        return inventoryNavigator;
    }

    public void setInventoryNavigator(final int inventoryNavigator) { this.inventoryNavigator = inventoryNavigator; }

    public void addToInventory(Item item) {
	inventory.add(item);
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public void removeFromInventory(Item item) {
	if (inventory.contains(item)) {
	    inventory.remove(item);
	}
    }

    public List<Item> getInventory() {
	return inventory;
    }
}
