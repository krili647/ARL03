package se.liu.ida.awesomeroguelike2003;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OpenInventoryAction extends AbstractAction
{
    private Game game;

    OpenInventoryAction(Game game){
	this.game = game;
    }

    @Override public void actionPerformed(final ActionEvent actionEvent) {
	switch (game.getGameState()){
	    case PLAYING:
		game.getPlayer().getInventoryScreenNavigator().setNavigator(0);
		game.setGameState(GameState.IN_INVENTORY);
		break;

	    case PICKINGUP:
		game.getPlayer().getInventoryScreenNavigator().setNavigator(0);
		game.setGameState(GameState.IN_INVENTORY);
		break;

	    case PLAYERDEAD:
		//When dead, close down everything
		game.getFrame().dispose();
		System.exit(0);
		break;

	    case IN_INVENTORY:
		game.setGameState(GameState.PLAYING);
		break;


	}

	game.gameUpdated();
    }
}
