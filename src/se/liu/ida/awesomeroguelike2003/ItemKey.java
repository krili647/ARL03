package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class ItemKey extends Item
{

    public ItemKey(final Game game) {
        super("Key", "It's an old rusty key", game);
    }


    @Override public void use() {
        int playerX = game.getPlayer().getX();
        int playerY = game.getPlayer().getY();

        //If a door is in an adjacent tile, open it
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Tile adjacentTile = game.getMap().getTileAt(playerX + x, playerY + y);

                if(adjacentTile.getClass().isInstance(new Door())) {
                    adjacentTile.setSolid(false);

                    //When used, remove from inventory
                    game.getPlayer().getInventory().removeFromInventory(this);
                    game.setGameState(GameState.PLAYING);
                    game.getMessageRoll().addMessage("Door was unlocked.");
                }
            }
        }

    }

    public String useMessage(){
        return "Door was unlocked";
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
	g.setColor(Color.YELLOW);
	g.fillRect(x* TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
