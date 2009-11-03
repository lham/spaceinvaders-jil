package gamecore;

public class Bullet extends ViewableObject implements MoveableObject{
    private double speed;
    private Direction direction;

    public Bullet(Direction direction, Coordinate spawnCoord, String imagePath) {
        super(spawnCoord, imagePath);
        this.speed = 0.45;
        this.direction = direction;
    }

    public void moveObject(Direction dir, long time) {
        if ((dir == Direction.UP) && (this.getArea().getTopRightCorner().getY() - (int)(this.speed*time) > 0)){
            this.getArea().moveArea(0, (int)(-this.speed*time));
        }
        else if ((dir == Direction.DOWN) && (this.getArea().getLowLeftCorner().getY() + (int)(this.speed*time) < Game.height)){
            this.getArea().moveArea(0, (int)(this.speed*time));
        }
    }
}
