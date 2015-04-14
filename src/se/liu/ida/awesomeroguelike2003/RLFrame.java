/*
 * Opens a frame where everything that can be seen in the game can be drawn
 * into a RLComponent
 */
package se.liu.ida.awesomeroguelike2003;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class RLFrame extends JFrame
{
    public RLFrame(Game game) {

	setTitle("Awesome R09V3|1k3");

	setResizable(false);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setPreferredSize(new Dimension(TestGame.WIDTH, TestGame.HEIGHT));

	add(game.getPaintComponent());

	pack();
	setVisible(true);
    }
}
