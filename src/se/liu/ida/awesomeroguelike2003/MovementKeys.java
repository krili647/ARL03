package se.liu.ida.awesomeroguelike2003;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MovementKeys extends AbstractAction
{
    Game game;
    Direction direction;
    int dx, dy;

    MovementKeys(Direction direction, Game game){
	this.game = game;
	this.direction = direction;

	switch (direction){
	    case UP:
		this.dx = 0;
		this.dy = -1;
		break;

	    case RIGHT:
		this.dx = 1;
		this.dy = 0;
		break;

	    case DOWN:
		this.dx = 0;
		this.dy = 1;
		break;

	    case LEFT:
		this.dx = -1;
		this.dy = 0;
		break;

	    default:
		this.dx = 0;
		this.dy = 0;
		break;
	}

    }

    @Override public void actionPerformed(final ActionEvent actionEvent) {

	switch (game.getGameState()){
	    case PLAYING:
		game.getPlayer().moveTo(dx, dy);
		break;

	    case PICKINGUP:
		switch (direction){
		    case UP:
			game.getPlayer().decrementInventoryNavigator();
			break;

		    case RIGHT:
			game.setGameState(GameState.PLAYING);
			break;

		    case DOWN:
			game.getPlayer().incrementInventoryNavigator();
			break;

		    case LEFT:
			game.setGameState(GameState.PLAYING);
			break;
		}
		break;

	    case IN_INVENTORY:
		switch (direction){
		    case UP:
			game.getPlayer().navigateInvScrUp();
			break;
		    case DOWN:
			game.getPlayer().navigateInvScrDown();
		}
		break;
	}

	game.gameUpdated();
    }


}
