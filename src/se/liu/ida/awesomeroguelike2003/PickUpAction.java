package se.liu.ida.awesomeroguelike2003;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PickUpAction extends AbstractAction
{
    private Game game;

    PickUpAction(Game game){
	this.game = game;
    }

    @Override public void actionPerformed(final ActionEvent actionEvent) {
	switch (game.getGameState()){
	    case PLAYING:
		game.getPlayer().pickUp();
		break;

	    case PICKINGUP:
		game.setGameState(GameState.PLAYING);
		break;

	    case PLAYERDEAD:
		//When dead, close down everything
		game.getFrame().dispose();
		System.exit(0);
		break;

	    case IN_INVENTORY:
		break;
	}

	game.gameUpdated();
    }
}
