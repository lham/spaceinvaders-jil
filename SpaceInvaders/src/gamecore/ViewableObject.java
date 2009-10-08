
package gamecore;

import java.awt.Graphics2D;

public abstract class ViewableObject {
    private Area area;
    
    public ViewableObject(Area area){
        this.area = area;
    }

    public abstract void drawObject(Graphics2D g);

    public Area getArea(){
        return this.area;
    }

}

