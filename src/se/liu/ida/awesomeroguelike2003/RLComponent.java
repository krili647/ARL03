/**
 * The component into which the game screen will be drawn
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class RLComponent extends JComponent
{
    private Game game;
    private Action moveUp, moveRight, moveDown, moveLeft, pickUp, enterAction, openInventory;

    public RLComponent(final Game game) {
	this.game = game;
	moveUp = new MovementAction(Direction.UP, game);
	moveRight = new MovementAction(Direction.RIGHT, game);
	moveDown = new MovementAction(Direction.DOWN, game);
	moveLeft = new MovementAction(Direction.LEFT, game);
	pickUp = new PickUpAction(game);
	enterAction = new EnterAction(game);
	openInventory = new OpenInventoryAction(game);
	assignKeys();
    }

    @Override public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;


	//Fill background with super metal pitch-black darkness
	g2d.setColor(Color.BLACK);
	g2d.fillRect(0, 0, TestGame.WIDTH, TestGame.HEIGHT);

	//Draw only the tiles that can be found in the scope
	final int startDrawingAtX = game.getPlayer().getX() - TestGame.SCOPEWIDTH / 2;
	final int startDrawingAtY = game.getPlayer().getY() - TestGame.SCOPEHEIGHT / 2;

	for (int x = 0; x < TestGame.SCOPEWIDTH; x++) {
	    for (int y = 0; y < TestGame.SCOPEHEIGHT; y++) {

		if (startDrawingAtX + x >= 0 && startDrawingAtY + y >= 0) {
		    if (startDrawingAtX + x < game.getMap().getMapWidth() && startDrawingAtY + y < game.getMap().getMapHeight()) {

			Tile visibleTile = game.getMap().getTileAt(startDrawingAtX + x, startDrawingAtY + y);
			if (visibleTile.isSeen()) {
			    visibleTile.draw(g2d, x, y);
			    for (Item i : visibleTile.getItems()) {
				i.draw(g2d, x, y);
			    }
			    if (visibleTile.getEntityHere() != null) {
				visibleTile.getEntityHere().draw(g2d, x, y);
			    }
			}
		    }
		}
	    }
	}

	drawHealthBar(g2d);

	drawSideBarInventory(g2d);

	drawMessageRoll(g2d);

	//Different screens for different game states
	if (game.getGameState() == GameState.PICKINGUP) {
	    drawItemScreen(g2d);
	}
	if (game.getGameState() == GameState.IN_INVENTORY) {
	    drawInventoryScreen(g2d);
	}
	if (game.getGameState() == GameState.PLAYERDEAD) {
	    drawGameOverScreen(g2d);
	}
    }

    private void drawMessageRoll(Graphics2D g2d) {
	game.getMessageRoll().draw(g2d);
    }

    private void drawHealthBar(Graphics2D g2d) {

	//Health indicated by a number on the screen
	g2d.setColor(Color.YELLOW);
	g2d.drawString("HEALTH: " + game.getPlayer().getHealthPoints(), 31 * TestGame.SQUARESIZE, 5 * TestGame.SQUARESIZE);

	//Health bar
	int maxHealth = 100;
	int currentHealth = (game.getPlayer().getHealthPoints() * 100 / maxHealth);

	g2d.drawRect(31 * TestGame.SQUARESIZE, 3 * TestGame.SQUARESIZE, maxHealth, TestGame.SQUARESIZE);
	g2d.fillRect(31*TestGame.SQUARESIZE, 3*TestGame.SQUARESIZE, currentHealth, TestGame.SQUARESIZE);
    }

    private void drawGameOverScreen(Graphics2D g2d) {
	//draw bg
	g2d.setColor(Color.black);
	g2d.fillRect(0, 0, TestGame.WIDTH, TestGame.HEIGHT);

	g2d.setColor(Color.YELLOW);
	g2d.drawString("GAME OVER \n You died, Press enter to exit.", TestGame.WIDTH / 4, TestGame.HEIGHT / 2);
    }

    private void drawItemScreen(Graphics2D g2d) {
	//draw background
	g2d.setColor(new Color(100, 100, 100, 150));
	g2d.fillRect(10, 10, 200, TestGame.HEIGHT);

	//draw items at player square
	List<Item> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems();

	int iterator = 0;

	for (Item o : items) {
	    //The items on the tile will be displayed in a list at the left side of the screen
	    o.draw(g2d, 1, iterator * 2 + 1);

	    if (game.getPlayer().getInventory().getInventoryNavigator() == iterator) {
		g2d.setColor(Color.YELLOW);
	    } else {
		g2d.setColor(Color.WHITE);
	    }

	    g2d.drawString(o.getName(), TestGame.SQUARESIZE * 3, (iterator * 2 + 2) * TestGame.SQUARESIZE - 5);

	    iterator++;

	}

    }

    void drawSideBarInventory(Graphics2D g2d) {
	//The items in the players inventory is being displayed in a box at the lower right corner of the screen
	g2d.setColor(Color.GRAY);
	g2d.fillRect(TestGame.SCOPEWIDTH * TestGame.SQUARESIZE, (TestGame.SCOPEHEIGHT - 5) * TestGame.SQUARESIZE,
		     TestGame.WIDTH - TestGame.SCOPEWIDTH * TestGame.SQUARESIZE,
		     TestGame.HEIGHT - (TestGame.SCOPEHEIGHT - 5) * TestGame.SQUARESIZE);
	List<Item> items = game.getPlayer().getInventory().getInventory();

	if (!items.isEmpty()) {
	    for (int x = 0; x < items.size(); x++) {
		int dx = TestGame.SCOPEWIDTH + x % 10;
		int dy = (TestGame.SCOPEHEIGHT - 5) + x / 10;

		items.get(x).draw(g2d, dx, dy);
	    }
	}
    }

    void drawInventoryScreen(Graphics2D g2d) {
	//Background
	g2d.setColor(Color.BLACK);
	g2d.fillRect(0, 0, TestGame.WIDTH, TestGame.HEIGHT);
	g2d.setColor(Color.YELLOW);
	g2d.drawString("INVENTORY \n \"i\" to exit / [enter] to use", TestGame.SQUARESIZE, TestGame.SQUARESIZE);

	//Items
	List<Item> items = game.getPlayer().getInventory().getInventory();

	int iterator = 0;

	for (Item o : items) {
	    //Draws the items in your inventory with a description of them
	    if (!o.equals(game.getPlayer())) {

		int yAxis = iterator % 14;
		int xAxis = iterator / 14;
		int xDrawAt = xAxis * 5 + 4;
		int yDrawAt = yAxis * 2 + 2;
		o.draw(g2d, xDrawAt, yDrawAt);

		if (game.getPlayer().getInventoryScreenNavigator().getNavigator() == iterator) {
		    g2d.setColor(Color.WHITE);
		    g2d.drawString(o.getDescription(), 20 * TestGame.SQUARESIZE, TestGame.SQUARESIZE);
		    g2d.setColor(Color.YELLOW);
		} else {
		    g2d.setColor(Color.WHITE);
		}

		//draw item name under the image of the item
		g2d.drawString(o.getName(), xDrawAt * TestGame.SQUARESIZE, (yDrawAt + 2) * TestGame.SQUARESIZE - 5);

		iterator++;
	    }
	}

    }

    private void assignKeys() {


	//Movement keys

	getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
	getActionMap().put("moveUp", moveUp);

	getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
	getActionMap().put("moveRight", moveRight);

	getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
	getActionMap().put("moveDown", moveDown);

	getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
	getActionMap().put("moveLeft", moveLeft);


	//Item actions

	getInputMap().put(KeyStroke.getKeyStroke("COMMA"), "pickUp");
	getActionMap().put("pickUp", pickUp);

	//Enter action

	getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
	getActionMap().put("enterAction", enterAction);

	//Open inventory

	getInputMap().put(KeyStroke.getKeyStroke("I"), "openInventory");
	getActionMap().put("openInventory", openInventory);

    }
}

