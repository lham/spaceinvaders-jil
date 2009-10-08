
package gamecore;

import java.awt.Graphics2D;

/**
 *
 * @author Isak
 */
public class Bullet extends ViewableObject implements MoveableObject{
    private int speed, direction;

    public Bullet() {
        //konstruktor
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void drawObject(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moveObject(int time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
