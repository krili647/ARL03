package se.liu.ida.awesomeroguelike2003;

import java.awt.*;

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
        //A*
    }
}
