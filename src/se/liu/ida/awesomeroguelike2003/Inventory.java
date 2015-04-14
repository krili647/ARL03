package se.liu.ida.awesomeroguelike2003;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    private List<GameObject> inventory = new ArrayList<GameObject>();

    private int inventoryNavigator = 0;

    public Inventory() {}

    public int getInventoryNavigator() {
        return inventoryNavigator;
    }

    public void setInventoryNavigator(final int inventoryNavigator) {
        this.inventoryNavigator = inventoryNavigator;
    }

    public Inventory(List<GameObject> items) {
	this.inventory = items;
    }

    public void addToInventory(GameObject item) {
	inventory.add(item);
    }

    public void removeFromInventory(Item item) {
	if (inventory.contains(item)) {
	    inventory.remove(item);
	}
    }

    public List<GameObject> getInventory() {
	return inventory;
    }
}
