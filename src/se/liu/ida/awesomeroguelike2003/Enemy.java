package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.Random;

/**
 * Created by leopold on 2015-07-12.
 */

public class Enemy extends Entity {

    public Enemy(final int x, final int y, final Game game) {
        super(x, y, game);
        this.healthPoints = 10;
        this.attackPoints = 10;
        this.defencePoints = 10;
    }

    @Override public void draw(final Graphics2D g, final int x, final int y) {
        g.setColor(new Color(0,0,0));
        g.fillOval(x*TestGame.SQUARESIZE, y*TestGame.SQUARESIZE, TestGame.SQUARESIZE, TestGame.SQUARESIZE);
    }

    @Override
    public void EntityAI() {
        //fulhackad pathfinding
        Random random = new Random();
        if (random.nextInt(3) < 2) {

        int playerX = game.getPlayer().getX();
        int playerY = game.getPlayer().getY();

        int dX = playerX - getX();
        int dY = playerY - getY();

        if (Math.pow(dX, 2) + Math.pow(dY, 2) < Math.pow(7, 2)) {

            if (dX != 0) {
                dX = dX / Math.abs(dX);
            }
            if (dY != 0) {
                dY = dY / Math.abs(dY);
            }

            moveTo(dX, dY);
        }
        }

    }
}
