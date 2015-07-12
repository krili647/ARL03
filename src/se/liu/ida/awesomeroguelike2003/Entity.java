package se.liu.ida.awesomeroguelike2003;

abstract class Entity extends GameObject
{
    private int healthPoints;
    private int attackPoints;
    private int defencePoints;
    private int x, y;
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
            game.getMap().getTileAt(x, y).removeFromEntities(this);
            x += dx;
            y += dy;
            game.getMap().getTileAt(x, y).addToEntities(this);
        }
    }

    boolean collision(final int dx, final int dy){
        return game.getMap().getTileAt(x + dx, y + dy).isSolid();
    }

    Entity(final int x, final int y, final Game game) {
        this.x = x;
        this.y = y;
        this.game = game;

    }

}
