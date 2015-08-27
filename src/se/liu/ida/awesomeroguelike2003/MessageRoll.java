package se.liu.ida.awesomeroguelike2003;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MessageRoll
{
    private List<String> messageRoll;

    public MessageRoll(){
	this.messageRoll = new ArrayList<>();
    }

    public void addMessage(final String message){
	if (message.length() > 100){
	    //If string is too long, cut it into multiple lines
	    messageRoll.add(message.substring(0,92));
	    addMessage(message.substring(92));
	} else {
	    messageRoll.add(message);
	}
    }

    public void draw(final Graphics2D g2d) {
    	g2d.setColor(Color.YELLOW);
    	int numOfVisibleLines = 5;

    	if (messageRoll.isEmpty()) {} else if (messageRoll.size() < numOfVisibleLines) {
    	    //draw the message roll
    	    for (int i = 0; i < messageRoll.size(); i++) {
    		g2d.drawString(messageRoll.get(i), TestGame.SQUARESIZE, (TestGame.SCOPEHEIGHT + 1 + i) * TestGame.SQUARESIZE);
    	    }
    	} else {
    	    //draw the five latest lines of the message roll
    	    for (int i = 0; i < numOfVisibleLines; i++) {
    		g2d.drawString(messageRoll.get(messageRoll.size() - numOfVisibleLines + i), TestGame.SQUARESIZE,
    			       (TestGame.SCOPEHEIGHT + 1 + i) * TestGame.SQUARESIZE);
    	    }
    	}
        }
}
