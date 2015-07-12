package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

public class ItemKey extends Item
{

    public ItemKey(final Game game) {
        super(game);
        this.description = "It's an old rusty key.";
        this.name = "Key";
    }

    @Override public void use() {
        int playerX = game.getPlayer().getX();
        int playerY = game.getPlayer().getY();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Tile tile = game.getMap().getTileAt(playerX + x, playerY + y);
                if(tile.getName() == "door" && tile.isSolid()) {
                    System.out.println("regis");
                    tile.setSolid(false);
                    game.getPlayer().getInventory().removeFromInventory(this);
                    game.setGameState(GameState.PLAYING);
                }
            }
        }

    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
	g.setColor(Color.YELLOW);
	g.fillRect(x* TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }
}
