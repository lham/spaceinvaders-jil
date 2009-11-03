package gamecore;

public class Bullet extends ViewableObject implements MoveableObject{
    private double speed;

    public Bullet(Coordinate spawnCoord, String imagePath) {
        super(spawnCoord, imagePath);
        this.speed = 0.45;
    }

    public void moveObject(Direction dir, long time) {
        if (dir == Direction.UP){
            this.getArea().moveArea(0, (int)(-this.speed*time));
        }
        else if (dir == Direction.DOWN){
            this.getArea().moveArea(0, (int)(this.speed*time));
        }
    }
}
