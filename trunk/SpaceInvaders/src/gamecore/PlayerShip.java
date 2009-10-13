
package gamecore;

import java.awt.Graphics2D;

/**
 *
 * @author Isak
 */
public class PlayerShip extends ViewableObject implements MoveableObject{
    private int speed;
    boolean hasActiveProjectile;

    public PlayerShip(Coordinate spawnCoord) {
        super(new Area(spawnCoord, new Coordinate(spawnCoord.getX(), spawnCoord.getY()))); //will change later to height and width of sprite
    }

    public void fireBullet() {
        //fire a bullet
    }

    public void moveShip() {
        //move the ship
    }

    public boolean hasActiveProjectile() {
        return hasActiveProjectile;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHasActiveProjectile(boolean hasActiveProjectile) {
        this.hasActiveProjectile = hasActiveProjectile;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void drawObject(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moveObject(int time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}