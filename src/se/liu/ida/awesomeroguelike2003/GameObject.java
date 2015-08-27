package se.liu.ida.awesomeroguelike2003;

abstract class GameObject implements Drawable
{
    String name = "Name";

    GameObject(final String name){
        this.name = name;
    }

    public String getName() {
	return name;
    }

}
