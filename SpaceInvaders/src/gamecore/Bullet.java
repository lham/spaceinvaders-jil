
package gamecore;

import java.awt.Graphics2D;

/**
 *
 * @author Isak
 */
public class Bullet extends ViewableObject implements MoveableObject{
    private int speed; 
    private Direction direction;

    public Bullet(Direction direction, Coordinate spawnCoord) {
        //konstruktor
        super(new Area(spawnCoord, new Coordinate(spawnCoord.getX(), spawnCoord.getY()+5)));
    }

    public void setSpeed(int speed) {
        this.speed = speed; 
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void drawObject(Graphics2D g) {
        
    }

    public void moveObject(long time) {
        super.getArea().moveArea(0, this.speed*time);
    }
}
