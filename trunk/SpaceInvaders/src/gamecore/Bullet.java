
package gamecore;

/**
 *
 * @author Isak
 */
public class Bullet {
    private int speed, direction;
    private int positionX, positionY;
    private int modelHeight, modelWidth;
    private int impactArea;

    public Bullet() {
        //konstruktor
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
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

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getSpeed() {
        return speed;
    }
}
