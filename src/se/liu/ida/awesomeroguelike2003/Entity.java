package se.liu.ida.awesomeroguelike2003;

public abstract class Entity extends GameObject
{
    private int healthPoints;
    private int attackPoints;
    private int defencePoints;
    private int x, y;
    protected Game game;

    public Game getGame() {
        return game;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(final int dx, final int dy) {
        if(!collision(dx, dy)) {
            game.getMap().getTileAt(x, y).removeFromGameObjects(this);
            x += dx;
            y += dy;
            game.getMap().getTileAt(x, y).addToGameObjects(this);
        }
    }

    public boolean collision(final int dx, final int dy){
        return game.getMap().getTileAt(x + dx, y + dy).isSolid();
    }

    public Entity(final int x, final int y, final Game game) {
        this.x = x;
        this.y = y;
        this.game = game;

    }

}
