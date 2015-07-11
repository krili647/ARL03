package se.liu.ida.awesomeroguelike2003;

public abstract class Item extends GameObject
{
    protected String description;

    protected Game game;

    public Item(final Game game) {
        this.game = game;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use();

}
