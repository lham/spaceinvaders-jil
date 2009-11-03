package gamecore;

public class PlayerShip extends ViewableObject implements MoveableObject{
    private double speed;
    private boolean hasActiveProjectile;
    private int bulletListIndex;

    public int getBulletListIndex() {
        return bulletListIndex;
    }

    public void setBulletListIndex(int i){
        this.bulletListIndex = i;
    }

    public PlayerShip(Coordinate spawnCoord, String imagePath) {
        super(spawnCoord, imagePath);
        this.hasActiveProjectile = false;
        this.speed = 0.2;
    }

    public boolean hasActiveProjectile() {
        return hasActiveProjectile;
    }
    
    public void setActiveProjectile(boolean hasActiveProjectile) {
        this.hasActiveProjectile = hasActiveProjectile;
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