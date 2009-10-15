package gamecore;

import java.awt.Graphics2D;

/**
 *
 * @author Linus
 */
public class House extends ViewableObject{
    private int health;
    //private Area houseSections[];

    public House(Coordinate spawnCoord) {
        super(new Area(spawnCoord, new Coordinate(spawnCoord.getX()+30, spawnCoord.getY()-40))); //will change later to height and width of sprite
    }

    public void destroyPart(/*Area destroyedPixels*/){

    }

    @Override
    public void drawObject(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}
