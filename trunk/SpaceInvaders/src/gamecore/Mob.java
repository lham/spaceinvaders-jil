package gamecore;

/**
 *
 * @author Jon
 */
public class Mob extends ViewableObject implements MoveableObject{
    private int value; // The amount of points earned by killing the mob
    private double speed;


    public Mob(int valueMultiplier, Coordinate spawnCoord, String imagePath, double speed){
        super(spawnCoord, imagePath);
        this.speed = speed;

        //Sätt monstrets poäng
        this.value = 10 * valueMultiplier;

    }

    public int getValue() {
        return value;
    }

    public double getSpeed(){
        return this.speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void moveObject(Direction dir, long time) {
        if (dir == Direction.LEFT){
            this.getArea().moveArea((int)(-this.speed*time), 0);
        }
        else if (dir == Direction.RIGHT){
            this.getArea().moveArea((int)(this.speed*time), 0);
        }
    }
}
