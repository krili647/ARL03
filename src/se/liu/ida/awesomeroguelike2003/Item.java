package se.liu.ida.awesomeroguelike2003;

public abstract class Item extends GameObject
{
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void use();

}
