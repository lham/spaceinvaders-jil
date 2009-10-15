
package gamecore;

import java.awt.Graphics2D;

/**
 *
 * @author Isak
 */
public class PlayerShip extends ViewableObject implements MoveableObject{
    private long speed;
    boolean hasActiveProjectile;

    public PlayerShip(Coordinate spawnCoord) {
        super(new Area(spawnCoord, new Coordinate(spawnCoord.getX()+30, spawnCoord.getY()-40))); //will change later to height and width of sprite
    }

    public void fireBullet() {
        //fire a bullet
    }

    public boolean hasActiveProjectile() {
        return hasActiveProjectile;
    }

    public long getSpeed() {
        return speed;
    }

    public void setHasActiveProjectile(boolean hasActiveProjectile) {
        this.hasActiveProjectile = hasActiveProjectile;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void drawObject(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moveObject(Direction dir, long time) {
        /* ship.area.högerkant > högerkant || ship.area.vänstarkant < vänsterkant*/
        if (super.getArea().getTopRightCorner().getX() > 800 /*Window.xSize*/ || super.getArea().getLowLeftCorner().getX() < 0){
            super.getArea().moveArea(this.speed*time, 0);
        }
        else {
            //don't move lololololololo
        }
    }
}