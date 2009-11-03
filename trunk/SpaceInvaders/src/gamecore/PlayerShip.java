package gamecore;

public class PlayerShip extends ViewableObject implements MoveableObject{
    private double speed;

    public PlayerShip(Coordinate spawnCoord, String imagePath) {
        super(spawnCoord, imagePath);
        this.speed = 0.2;
    }

    public void moveObject(Direction dir, long time) {
        if ((dir == Direction.LEFT) && (this.getArea().getLowLeftCorner().getX() - (int)(this.speed*time) > 0)){
            this.getArea().moveArea((int)(-this.speed*time), 0);
        }
        else if ((dir == Direction.RIGHT) && (this.getArea().getTopRightCorner().getX() + (int)(this.speed*time) < Game.width)){
            this.getArea().moveArea((int)(this.speed*time), 0);
        }
        else {
            //Ingen förflyttning kunde ske
        }
    }
}