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
            if (tile.isSeen()) {
                tile.draw(g2d, x, y);
                for (Item i : tile.getItems()) {
                    i.draw(g2d, x, y);
                }
                if (tile.getEntityHere() != null) {
                    tile.getEntityHere().draw(g2d, x, y);
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
        g2d.setColor(Color.YELLOW);
        List<String> roll = game.getMessageRoll();

        int maxNr = 5;

        if (roll.isEmpty()) {}
        else if (roll.size() < maxNr) {
            //rita det som finns
            for (int i = 0; i < roll.size(); i++) {
                g2d.drawString(roll.get(i), TestGame.SQUARESIZE,(TestGame.SCOPEHEIGHT + 1 + i)*TestGame.SQUARESIZE);
            }
        } else {
            //rita senaste fem
            for (int i = 0; i < maxNr; i++) {
                g2d.drawString(roll.get(roll.size() - maxNr + i),TestGame.SQUARESIZE,(TestGame.SCOPEHEIGHT + 1 + i)*TestGame.SQUARESIZE);
            }
        }
    }

    private void drawHealthBar(Graphics2D g2d) {
		//Health indicated by a number on the screen
        g2d.setColor(Color.YELLOW);
        g2d.drawString("HEALTH: " + game.getPlayer().getHealthPoints(), 31 * TestGame.SQUARESIZE, 5 * TestGame.SQUARESIZE);
    }

    private void drawGameOverScreen(Graphics2D g2d) {
        //draw bg
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, TestGame.WIDTH, TestGame.HEIGHT);

        g2d.setColor(Color.YELLOW);
        g2d.drawString("GAME OVER \n You died, Press enter to exit.", TestGame.WIDTH/4, TestGame.HEIGHT/2);
    }

    private void drawItemScreen(Graphics2D g2d) {
	//draw background
	g2d.setColor(new Color(100, 100, 100, 150));
	g2d.fillRect(10, 10, 200, TestGame.HEIGHT);

	//draw items at player square
	List<Item> items = game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems();

	int iterator = 0;

	for(Item o : items) {
		//The items on the tile will be displayed in a list at the left side of the screen
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

    void drawSideBarInventory(Graphics2D g2d) {
    //The items in the players inventory is being displayed in a box at the lower right corner of the screen
	g2d.setColor(Color.GRAY);
	g2d.fillRect(TestGame.SCOPEWIDTH * TestGame.SQUARESIZE, (TestGame.SCOPEHEIGHT - 5) * TestGame.SQUARESIZE,
            TestGame.WIDTH - TestGame.SCOPEWIDTH * TestGame.SQUARESIZE, TestGame.HEIGHT - (TestGame.SCOPEHEIGHT - 5) * TestGame.SQUARESIZE);
	List<Item> items = game.getPlayer().getInventory().getInventory();

	if (!items.isEmpty()) {
	    for (int x = 0; x < items.size(); x++) {
		int dx = TestGame.SCOPEWIDTH + x%10;
		int dy = (TestGame.SCOPEHEIGHT - 5) + x/10;

		items.get(x).draw(g2d, dx, dy);
	    }
	}
    }

    void drawInventoryScreen(Graphics2D g2d) {
	//Background
	g2d.setColor(Color.BLACK);
	g2d.fillRect(0,0, TestGame.WIDTH, TestGame.HEIGHT);
	g2d.setColor(Color.YELLOW);
	g2d.drawString("INVENTORY \n \"i\" to exit / [enter] to use", TestGame.SQUARESIZE, TestGame.SQUARESIZE);

	//Items
	List<Item> items = game.getPlayer().getInventory().getInventory();

	int iterator = 0;

	for(Item o : items) {
		//Draws the items in your inventory with a description of them
	    if (!o.equals(game.getPlayer())) {

		int yAxis = iterator % 14;
		int xAxis = iterator / 14;
		int xDrawAt = xAxis*5 + 4;
		int yDrawAt = yAxis*2 + 2;
		o.draw(g2d, xDrawAt, yDrawAt);

		if(game.getPlayer().getInventoryScreenNavigator().getNavigator() == iterator) {
            g2d.setColor(Color.WHITE);
            g2d.drawString(o.getDescription(), 20*TestGame.SQUARESIZE, TestGame.SQUARESIZE);
            g2d.setColor(Color.YELLOW);
		} else {
		    g2d.setColor(Color.WHITE);
		}

        //draw item name under the image of the item
		g2d.drawString(o.getName(), xDrawAt*TestGame.SQUARESIZE, (yDrawAt + 2)*TestGame.SQUARESIZE - 5);

		iterator++;
	    }
    }

    }

    private void assignKeys() {

	//Movement keys

	getInputMap().put(KeyStroke.getKeyStroke("NUMPAD8"), "goNorth");
	final Action pressedUp = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().moveTo(0, -1);
		} else if(game.getGameState() == GameState.PICKINGUP) {
		    game.getPlayer().decrementInventoryNavigator();
		} else if (game.getGameState() == GameState.IN_INVENTORY) {
		    game.getPlayer().navigateInvScrUp();
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
		} else if (game.getGameState() == GameState.IN_INVENTORY) {
		    game.getPlayer().navigateInvScrDown();
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

	//Item actions

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
		}
        if (game.getGameState() == GameState.IN_INVENTORY) {
            game.getPlayer().useSelectedItem();
        }
        if (game.getGameState() == GameState.PLAYERDEAD) {
            game.getRLFrame().dispose();
            System.exit(0);
        }
        if (game.getGameState() == GameState.PLAYING) {
            if (game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getName() == "up" ) {
                //move up

                if (game.getLevelNumber() == 0) {
                    //if player has collected the orb of zot the player wins
                    for (Item i: game.getPlayer().getInventory().getInventory()) {
                        if (i instanceof ItemOrbOfZot) {
                            System.out.println("    GAME OVER \nYOU HAVE WON ETERNAL GLORY!");
                        }
                    }
                    game.getRLFrame().dispose();
                    System.exit(0);
                } else {

                    //Reduce the level number
                    game.setLevelNumber(game.getLevelNumber() - 1);

                    //remove player from the previous level
                    game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).removeFromEntities(game.getPlayer());

                    //Set the game for the new level
                    Map nextLevel = game.getLevels().get(game.getLevelNumber());
                    game.setMap(nextLevel);

                    //Move the player
                    game.getPlayer().setX(nextLevel.getStaircaseDownX());
                    game.getPlayer().setY(nextLevel.getStaircaseDownY());
                    game.getMap().getTileAt(nextLevel.getStaircaseDownX(),nextLevel.getStaircaseDownY()).addToEntities(game.getPlayer());

                }
            } else if (game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getName() == "down" ) {
                //Move down

                //Increase the level number
                game.setLevelNumber(game.getLevelNumber() + 1);

                //Remove the player from the previous level
                game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).removeFromEntities(game.getPlayer());

                //Set the game for the new level
                Map nextLevel = game.getLevels().get(game.getLevelNumber());
                game.setMap(nextLevel);

                //Move the player
                game.getPlayer().setX(nextLevel.getStaircaseUpX());
                game.getPlayer().setY(nextLevel.getStaircaseUpY());
                game.getMap().getTileAt(nextLevel.getStaircaseUpX(),nextLevel.getStaircaseUpY()).addToEntities(game.getPlayer());


            }
        }
        game.gameUpdated();
        }


	};
	getActionMap().put("pickUpSelected", pressedEnter);

	//Open inventory

	getInputMap().put(KeyStroke.getKeyStroke("I"), "openInventory");

	final Action pressedI = new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if(game.getGameState() == GameState.PLAYING) {
		    game.getPlayer().getInventoryScreenNavigator().setNavigator(0);
		    game.setGameState(GameState.IN_INVENTORY);
		}else if(game.getGameState() == GameState.IN_INVENTORY) {
		    game.setGameState(GameState.PLAYING);

		}
		game.gameUpdated();
	    }
	};
	getActionMap().put("openInventory", pressedI);


    }
}
