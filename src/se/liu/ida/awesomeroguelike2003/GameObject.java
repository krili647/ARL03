package se.liu.ida.awesomeroguelike2003;

abstract class GameObject implements Drawable
{
    protected String name = "Name";

    protected int r, g, b;

    GameObject(final String name, final int r, final int g, final int b){
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String getName() {
	return name;
    }

}
