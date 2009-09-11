
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

    public int getImpactArea() {
        return impactArea;
    }

    public int getModelHeight() {
        return modelHeight;
    }

    public int getModelWidth() {
        return modelWidth;
    }

    public int getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHasActiveProjectile(boolean hasActiveProjectile) {
        this.hasActiveProjectile = hasActiveProjectile;
    }

    public void setImpactArea(int impactArea) {
        this.impactArea = impactArea;
    }

    public void setModelHeight(int modelHeight) {
        this.modelHeight = modelHeight;
    }

    public void setModelWidth(int modelWidth) {
        this.modelWidth = modelWidth;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
