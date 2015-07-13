package se.liu.ida.awesomeroguelike2003;

import java.util.Random;

abstract class Entity extends GameObject implements GameAI
{
    protected int healthPoints;
    protected int attackPoints;
    protected int defencePoints;
    protected int x, y;
    final Game game;

    Game getGame() {
        return game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveTo(final int dx, final int dy) {
        if(!collision(dx, dy)) {
            if (game.getMap().getTileAt(x + dx, y + dy).getEntityHere() != null) {
                System.out.println("HEEEJ");
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

    Entity(final int x, final int y, final Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        game.getMap().getTileAt(x, y).addToEntities(this);

    }

    private void attackOther(final Entity self, final Entity other) {
        Random random = new Random();
        int damage = random.nextInt(self.attackPoints);
        other.healthPoints -= damage;
        System.out.println(damage);
    }

}
