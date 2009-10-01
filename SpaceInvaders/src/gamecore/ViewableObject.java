
package gamecore;

import java.awt.Graphics2D;

public abstract class ViewableObject {
    private Area impactArea;
    private Coordinate position;
    
    public ViewableObject(Area impactArea, Coordinate startPos){
        this.impactArea = impactArea;
        this.position = startPos;
    }

    public abstract void drawObject(Graphics2D g);

}
