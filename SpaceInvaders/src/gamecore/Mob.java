package gamecore;

public class Mob {
    private int xPos, yPos; //Positioning
    private int speed;  //The speed in pixels/sec
    private int modelHeight, modelWidth;
    private int value; // The amount of points earned by killing the mob
    private int direction; //The direction of the mob

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setModelHeight(int modelHeight) {
        this.modelHeight = modelHeight;
    }

    public void setModelWidth(int modelWidth) {
        this.modelWidth = modelWidth;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    public int getDirection() {
        return direction;
    }

    public int getModelHeight() {
        return modelHeight;
    }

    public int getModelWidth() {
        return modelWidth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getValue() {
        return value;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
