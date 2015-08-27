package se.liu.ida.awesomeroguelike2003;

import java.util.Random;

abstract class Entity extends GameObject implements EntityBehaviour
{
    protected int healthPoints;
    protected int attackPoints;
    protected int x, y;
    protected final Game game;

    Entity(final int x, final int y,
           final int healthPoints, final int attackPoints,
           final String name, final int r, final int g, final int b, final Game game) {
        super(name, r, g, b);
        this.x = x;
        this.y = y;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.game = game;

    }

    Game getGame() {
        return game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void moveTo(final int dx, final int dy) {
        //If another entity stands in the way, attack it, otherwise move.
        if(!collision(dx, dy)) {
            if (game.getMap().getTileAt(x + dx, y + dy).getEntityHere() != null) {
                attackOther(this, game.getMap().getTileAt(x + dx, y + dy).getEntityHere());
            } else {
                game.getMap().getTileAt(x, y).removeFromEntities(this);
                x += dx;
                y += dy;
                game.getMap().getTileAt(x, y).addToEntities(this);
            }
        }
    }

    boolean collision(final int dx, final int dy){

        return game.getMap().getTileAt(x + dx, y + dy).isSolid();

    }

    private void attackOther(final Entity self, final Entity other) {
        //Checks stats for both the attacked and the attacker to set health points of the attacked
        Random random = new Random();
        int damage = random.nextInt(self.attackPoints);
        other.healthPoints -= damage;
        game.getMessageRoll().addMessage(other.getName() + " took " + damage  + " damage!");
    }

}
