/*
 * Interface for all the things in a map that are visible
 */

package se.liu.ida.awesomeroguelike2003;

import javax.swing.*;
import java.awt.*;

interface Drawable
{
    void draw(Graphics2D g, final int x, final int y);
}
