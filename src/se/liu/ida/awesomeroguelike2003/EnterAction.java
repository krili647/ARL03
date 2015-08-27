package se.liu.ida.awesomeroguelike2003;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterAction extends AbstractAction
{
    private Game game;

    EnterAction(Game game){
	this.game = game;
    }

    @Override public void actionPerformed(final ActionEvent actionEvent) {

	switch (game.getGameState()){
	    case PICKINGUP:
		//If you pick up the Orb of Zot you win the game
		if (game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getItems().get(0) instanceof ItemOrbOfZot){
		    //WINNING
		    System.out.println("GAME OVER \nYOU HAVE WON ETERNAL GLORY!");
		    game.getFrame().dispose();
		    System.exit(0);
		} else {
		    game.getPlayer().pickUpSelectedItem();
		}
		break;

	    case IN_INVENTORY:
		game.getPlayer().useSelectedItem();
		break;

	    case PLAYERDEAD:
		//When dead, close down everything
		game.getFrame().dispose();
		System.exit(0);
		break;

	    case PLAYING:
		//Walk through staircases
		if (game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getClass()
			.isInstance(new StaircaseUp())) {
		    //Move up
		    game.changeLevel(game.getLevelNumber() - 1);

		} else if (game.getMap().getTileAt(game.getPlayer().getX(), game.getPlayer().getY()).getClass()
			.isInstance(new StaircaseDown())) {
		    //Move down
		    game.changeLevel(game.getLevelNumber() + 1);
		}
		break;
	}

	game.gameUpdated();

    }
}
