package gamecore;

/**
 * Coordinate contains the x-value and y-value of a pixel on the screen.
 * @author Linus
 */
public class Coordinate {
    private int x, y;

    /**
     * Constructor, sets the values of the Coordinate.
     * @param x The value x will be assigned.
     * @param y The value y will be assigned.
     */
    public Coordinate (int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-value of the Coordinate.
     * @return x-value of the Coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * Set the x-value of the Coordinate.
     * @param x The value x will be assigned.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Returns the y-value of the Coordinate.
     * @return y-value of the Coordinate
     */
    public int getY(){
        return this.y;
    }

    /**
     * Set the y-value of the Coordinate.
     * @param y The value y will be assigned.
     */
    public void setY(int y){
        this.y = y;
    }

    @Override
    public String toString(){
        return "x=" + this.x + "; y=" + this.y;
    }
}
