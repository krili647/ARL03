/**
 * The component into which the game screen will be drawn
 */

package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class RLComponent extends JComponent
{
    private Game game;

    public RLComponent(final Game game) {
	this.game = game;
	assignKeys();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;


	//Fill background with super metal pitch-black darkness
	g2d.setColor(Color.BLACK);
	g2d.fillRect(0,0,TestGame.WIDTH, TestGame.HEIGHT);

	//Draw only the tiles that can be found in the scope
	final int startDrawingAtX = game.getPlayer().getX() - TestGame.SCOPEWIDTH/2;
	final int startDrawingAtY = game.getPlayer().getY() - TestGame.SCOPEHEIGHT/2;

	for(int x = 0; x < TestGame.SCOPEWIDTH; x++) {
	    for(int y = 0; y < TestGame.SCOPEHEIGHT; y++) {

		if (startDrawingAtX + x >= 0 && startDrawingAtY  + y >= 0) {
		    if(startDrawingAtX + x < game.getMap().getMapWidth() && startDrawingAtY  + y < game.getMap().getMapHeight()) {

			Tile tile = game.getMap().getTileAt(startDrawingAtX + x, startDrawingAtY + y);
			tile.draw(g2d, x, y);
			if (!tile.isSolid()) {
			    for (GameObject o : tile.getGameObjects()) {
				o.draw(g2d, x, y);
			    }
			}
		    }
		}
	    }
	}

	drawSideBarInventory(g2d);

	if (game.getGameState() == GameState.PICKINGUP) {
	    drawItemScreen(g2d);
	}
	if (game.getGameState() == GameState.IN_INVENTORY) {
	    drawInventoryScreen(g2d);
	}
    }

    private void drawItemScreen(Graphics2D g2d) {
	//draw background
	g2d.setColor(new Color(100, 100, 100, 150));
	g2d.fillRect(10, 10, 200, TestGame.HEIGHT);

	//draw items at player square
	List<GameObject> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getGameObjects();

	int iterator = 0;

	for(GameObject o : items) {
	    if (!o.equals(game.getPlayer())) {
		o.draw(g2d, 1, iterator*2 + 1);

		if(game.getPlayer().getInventory().getInventoryNavigator() == iterator) {
		    g2d.setColor(Color.YELLOW);
		} else {
		    g2d.setColor(Color.WHITE);
		}

		g2d.drawString(o.getName(), TestGame.SQUARESIZE*3, (iterator*2 + 2)*TestGame.SQUARESIZE - 5);

		iterator++;
	    }
	}

    }

    public void drawSideBarInventory(Graphics2D g2d) {
	g2d.setColor(Color.GRAY);
	g2d.fillRect(TestGame.SCOPEWIDTH*TestGame.SQUARESIZE, (TestGame.SCOPEHEIGHT - 5)*TestGame.SQUARESIZE,
		     TestGame.WIDTH - TestGame.SCOPEWIDTH*TestGame.SQUARESIZE, TestGame.HEIGHT - (TestGame.SCOPEHEIGHT - 5)*TestGame.SQUARESIZE);
	List<GameObject> items = game.getPlayer().getInventory().getInventory();

	if (!items.isEmpty()) {
	    for (int x = 0; x < items.size(); x++) {
		int dx = TestGame.SCOPEWIDTH + x%10;
		int dy = (TestGame.SCOPEHEIGHT - 5) + x/10;

		items.get(x).draw(g2d, dx, dy);
	    }
	}
    }

    public void drawInventoryScreen(Graphics2D g2d) {
	//Background
	g2d.setColor(new Color(0,0,0));
	g2d.fillRect(0,0, TestGame.WIDTH, TestGame.HEIGHT);
	g2d.setColor(Color.YELLOW);
	g2d.drawString("INVENTORY \n \"i\" to Exit", TestGame.SQUARESIZE, TestGame.SQUARESIZE);

	//Items
	List<GameObject> items = game.getPlayer().getInventory().getInventory();

	int iterator = 0;

	for(GameObject o : items) {
	    if (!o.equals(game.getPlayer())) {

		int yAxis = iterator % 15;
		int xAxis = iterator / 15;
		int xDrawAt = xAxis*5 + 4;
		int yDrawAt = yAxis*2 + 2;
		o.draw(g2d, xDrawAt, yDrawAt);

		if(game.getPlayer().getInventory().getInventoryNavigator() == iterator) {
		    g2d.setColor(Color.YELLOW);
		} else {
		    g2d.setColor(Color.WHITE);
		}

		g2d.drawString(o.getName(), xDrawAt*TestGame.SQUARESIZE, (yDrawAt + 2)*TestGame.SQUARESIZE - 5);

		iterator++;
	    }
	}

    }

    private void assignKeys() {
	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD8"), "goNorth");
	final Action pressedUp = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(0, -1);
		} else if(game.getGameState() == GameState.PICKINGUP) {
		    game.getPlayer().decrementInventoryNavigator();
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goNorth", pressedUp);

	getInputMap().put(KeyStroke.getKeyStroke("UP"), "goNorth");

	getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "goSouth");
	final Action pressedDown = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(0, 1);
		} else if(game.getGameState() == GameState.PICKINGUP) {
		    game.getPlayer().incrementInventoryNavigator();
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goSouth", pressedDown);

	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD2"), "goSouth");


	//Go E or navigate menu
	getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "goEast");
	final Action pressedRight = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(1, 0);

		}else if(game.getGameState() == GameState.PICKINGUP) {
		    game.setGameState(GameState.PLAYING);
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goEast", pressedRight);

	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD6"), "goEast");

	//Go west or navigate menu
	getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goWest");
	final Action pressedLeft = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(-1, 0);

		}else if(game.getGameState() == GameState.PICKINGUP) {
		    game.setGameState(GameState.PLAYING);
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goWest", pressedLeft);

	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD4"), "goWest");


	//Go NW or navigate menu
	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD7"), "goNorthWest");
	final Action pressedSeven = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(-1, -1);

		}else if(game.getGameState() == GameState.PICKINGUP) {
		    game.setGameState(GameState.PLAYING);
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goNorthWest", pressedSeven);

	//Go NE or navigate menu
	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD9"), "goNorthEast");
	final Action pressedNine = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(1, -1);

		}else if(game.getGameState() == GameState.PICKINGUP) {
		    game.setGameState(GameState.PLAYING);
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goNorthEast", pressedNine);

	//Go SW or navigate menu
	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD1"), "goSouthWest");
	final Action pressedOne = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(-1, 1);
		}else if(game.getGameState() == GameState.PICKINGUP) {
		    game.setGameState(GameState.PLAYING);
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goSouthWest", pressedOne);


	//Go SE or navigate menu
	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD3"), "goSouthEast");
	final Action pressedThree = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(1, 1);
		}else if(game.getGameState() == GameState.PICKINGUP) {
		    game.setGameState(GameState.PLAYING);
		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("goSouthEast", pressedThree);

	getInputMap().put(KeyStroke.getKeyStroke("COMMA"), "pickUp");

	final Action pressedComma = new AbstractAction()
	    {
		@Override public void actionPerformed(ActionEvent e) {
		    if(game.getGameState() == GameState.PICKINGUP) {
			game.setGameState(GameState.PLAYING);
			game.gameUpdated();
		    } else if(game.getGameState() == GameState.PLAYING) {
			game.getPlayer().pickUp();
			game.gameUpdated();
		    }
	    }
	};
	getActionMap().put("pickUp", pressedComma);

	getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pickUpSelected");

	final Action pressedEnter = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if (game.getGameState() == GameState.PICKINGUP) {
		    game.getPlayer().pickUpSelectedItem();
		    game.gameUpdated();
		}
	    }
	};
	getActionMap().put("pickUpSelected", pressedEnter);

	//open inventory

	getInputMap().put(KeyStroke.getKeyStroke("I"), "openInventory");

	final Action pressedI = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.setGameState(GameState.IN_INVENTORY);
		    game.gameUpdated();
		}else if(game.getGameState() == GameState.IN_INVENTORY) {
		    game.setGameState(GameState.PLAYING);
		    game.gameUpdated();
		}
	    }
	};
	getActionMap().put("openInventory", pressedI);


    }
}
