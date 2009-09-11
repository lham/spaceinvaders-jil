
package gamecore;

/**
 *
 * @author Isak
 */
public class PlayerShip extends ViewableObject {
    private int speed;
    boolean hasActiveProjectile;

    public PlayerShip() {
        //konstruktor
    }

    public void fireBullet() {
        //avfyra ett skott
    }

    public void moveShip() {
        //flytta skeppet
    }

    public boolean isHasActiveProjectile() {
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
}
