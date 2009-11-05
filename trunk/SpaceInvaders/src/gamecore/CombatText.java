package gamecore;

import java.awt.Font;
import java.awt.Graphics2D;

public class CombatText {
    private long timestamp;
    private String message;
    private boolean freeze;
    private long visibleTime = 300;

    public void addMessage(String message, boolean freezeTillNext){
        this.timestamp = System.currentTimeMillis();
        this.message = message;
        this.freeze = freezeTillNext;
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public String getMessage(){
        return this.message;
    }

    public void drawCombatText(Graphics2D g, int ypos){
        g.setFont(new Font("Courier New", Font.BOLD, 30));

        if ((System.currentTimeMillis() - this.timestamp > this.visibleTime) && !this.freeze)
            g.drawString("", (Game.width - g.getFontMetrics().stringWidth(""))/2, ypos);
        else
            g.drawString(this.message, (Game.width - g.getFontMetrics().stringWidth(this.message))/2, ypos);
            
    }

    public void setVisibleTime(long time){
        this.visibleTime = time;
    }

    public void clearMessage(){
        this.message = "";
    }
}
