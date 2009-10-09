package gamecore;

import java.awt.Graphics2D;

/**
 *
 * @author Jon
 */
public class Mob extends ViewableObject implements MoveableObject{
    private int value; // The amount of points earned by killing the mob


    public Mob(int valueMultiplier, Coordinate spawnCoord){
        //Här skall storleken skrivas in (dvs hur stor area moben tar up, ex 20x20 px)
        //Bör bytas ut mot spawnCoord.getX()+mobGrapic.getWidth();
        super(new Area(spawnCoord, new Coordinate(spawnCoord.getX()+20, spawnCoord.getY()-20)));

        //Sätt monstrets poäng
        this.value = 10 * valueMultiplier;

    }

    public int getValue() {
        return value;
    }

    @Override
    public void drawObject(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moveObject(Direction dir, long time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
