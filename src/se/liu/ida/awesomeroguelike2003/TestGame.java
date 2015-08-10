package se.liu.ida.awesomeroguelike2003;

final class TestGame
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    //Each tile has a dimension of 20 pixels
    public static final int SQUARESIZE = 20;

    //The scope is the part of the map that is visible on the screen
    //The scope is measured in tiles
    public static final int SCOPEWIDTH = 30;
    public static final int SCOPEHEIGHT = 24;

    public static final int INVENTORYCAPACITY = 105;

    private TestGame() {}

    public static void main(String[] args) {
        Game game = new Game();
    }
}
