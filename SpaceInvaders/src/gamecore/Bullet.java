
package gamecore;

/**
 *
 * @author Isak
 */
public class Bullet extends ViewableObject{
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
}
