package se.liu.ida.awesomeroguelike2003;

@SuppressWarnings("ALL")
abstract class Item extends GameObject
{
    protected String description;

    protected Game game;

    public Item(final String name, final String description, final int r, final int g, final int b, final Game game) {
        super(name, r, g, b);
        this.description = description;
        this.game = game;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use();

}
