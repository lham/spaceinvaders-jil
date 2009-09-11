package gamecore;

public class Mob {
    private int speed;  //The speed in pixels/sec
    private int value; // The amount of points earned by killing the mob
    private int direction; //The direction of the mob

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getValue() {
        return value;
    }
}
