package gamecore;

/**
 * Area contains information about a square area of pixels on the screen. The
 * area must have 4 corners which each are 90 degrees. Requires the Coordinate
 * class to work properly.
 * @author Linus
 */
public class Area {
    private Coordinate lowLeftCorner, topRightCorner;

    /**
     * Constructor, sets the area from of two Coordinates.
     * @param lowLeftCorner     The Coordinate for the lowLeftCorner.
     * @param topRightCorner    the Coordinate for the topRightcorner.
     */
    public Area (Coordinate lowLeftCorner, Coordinate topRightCorner){
        if (lowLeftCorner.getX() <= topRightCorner.getX() && lowLeftCorner.getY() >= topRightCorner.getY()){
            this.lowLeftCorner = lowLeftCorner;
            this.topRightCorner = topRightCorner;
        }
        else {
            //Kasta exception eller något käckt..
        }
    }

    /**
     * Returns the lenght of the bottom or top of the area.
     * @return The lenght of the base of the area.
     */
    public int getLenght(){
        return this.topRightCorner.getX() - this.lowLeftCorner.getX() + 1;
    }

    /**
     * Returns the height of the right or left side of the area.
     * @return The height of the side of the area.
     */
    public int getHeight(){
        return this.lowLeftCorner.getY() - this.topRightCorner.getY() + 1;
    }


    /**
     * Returns the Coordinate of the lowLeftCorner.
     * @return Coordinate of the lowLeftCorner.
     */
    public Coordinate getLowLeftCorner(){
        return this.lowLeftCorner;
    }

    /**
     * Returns the Coordinate of the lowRightCorner.
     * @return Coordinate of the lowRightCorner.
     */
    public Coordinate getLowRightCorner(){
        return new Coordinate(this.topRightCorner.getX(), this.lowLeftCorner.getY());
    }

    /**
     * Returns the Coordinate of the topRightCorner.
     * @return Coordinate of the topRightCorner.
     */
    public Coordinate getTopRightCorner(){
        return this.topRightCorner;
    }

    /**
     * Returns the Coordinate of the topLeftCorner.
     * @return Coordinate of the topLeftCorner.
     */
    public Coordinate getTopLeftCorner(){
        return new Coordinate(this.lowLeftCorner.getX(), this.topRightCorner.getY());
    }
    /**
     * Set the position of the lowLeftCorner.
     * @param newX The new x-value.
     * @param newY The new y-value.
     * @return <code>true</code> if the corner was set successfully, otherwise
     * it returns <code>false</code>
     */
    public boolean setLowLeftCorner(int newX, int newY){
        if (newX > this.topRightCorner.getX() || newY < this.topRightCorner.getY()){
            return false;
        }
        else {
            this.lowLeftCorner.setX(newX);
            this.lowLeftCorner.setY(newY);
            return true;
        }
    }

    /**
     * Set the position of the lowLeftCorner.
     * @param coord The Coordinate lowLeftCorner will be assigned to.
     * @return <code>true</code> if the corner was set successfully, otherwise
     * it returns <code>false</code>
     */
    public boolean setLowLeftCorner(Coordinate coord){
        if (this.setLowLeftCorner(coord.getX(), coord.getY()))
            return true;
        else
            return false;
    }

    /**
     * Set the position of the topRightCorner.
     * @param newX The new x-value.
     * @param newY The new y-value.
     * @return <code>true</code> if the corner was set successfully, otherwise
     * it returns <code>false</code>
     */
    public boolean setTopRightCorner(int newX, int newY){
        if (newX < this.lowLeftCorner.getX() || newY > this.lowLeftCorner.getY()){
            return false;
        }
        else {
            this.topRightCorner.setX(newX);
            this.topRightCorner.setY(newY);
            return true;
        }
    }

    /**
     * Set the position of the topRightCorner.
     * @param coord The Coordinate topRightCorner will be assigned to.
     * @return <code>true</code> if the corner was set successfully, otherwise
     * it returns <code>false</code>
     */
    public boolean setTopRightCorner(Coordinate coord){
        if (this.setTopRightCorner(coord.getX(), coord.getY()))
            return true;
        else
            return false;
    }

    /**
     * Moves the lowLeftCorner of the area x steps in x-direction and y steps in
     * y-direction
     * @param moveX How much the corner will move in x-direction.
     * @param moveY How much the corner will move in y-direction.
     * @return <code>true</code> if the corner was moved successfully, otherwise
     * it returns <code>false</code>
     */
    public boolean moveLowLeftCorner(int moveX, int moveY){
        if (this.lowLeftCorner.getX() + moveX > this.topRightCorner.getX()){
            return false;
        }
        else if (this.lowLeftCorner.getY() + moveY < this.topRightCorner.getY()){
            return false;
        }
        else {
            this.lowLeftCorner.setX(this.lowLeftCorner.getX() + moveX);
            this.lowLeftCorner.setY(this.lowLeftCorner.getY() + moveY);
            return true;
        }
    }

    /**
     * Moves the topRightCorner of the area x steps in x-direction and y steps in
     * y-direction
     * @param moveX How much the corner will move in x-direction.
     * @param moveY How much the corner will move in y-direction.
     * @return <code>true</code> if the corner was moved successfully, otherwise
     * it returns <code>false</code>
     */
    public boolean moveTopRightCorner(int moveX, int moveY){
        if (this.topRightCorner.getX() + moveX < this.lowLeftCorner.getX()){
            return false;
        }
        else if (this.topRightCorner.getY() + moveY > this.lowLeftCorner.getY()){
            return false;
        }
        else {
            this.topRightCorner.setX(this.topRightCorner.getX() + moveX);
            this.topRightCorner.setY(this.topRightCorner.getY() + moveY);
            return true;
        }
    }

    /**
     * Moves the Area x steps in x-direction and y steps in y-direction.
     * @param moveX How much the area will move in x-direction.
     * @param moveY How much the area will move in y-direction.
     */
    public void moveArea(int moveX, int moveY){
        this.setArea(
                    new Coordinate(this.lowLeftCorner.getX() + moveX, this.lowLeftCorner.getY() + moveY),
                    new Coordinate(this.topRightCorner.getX() + moveX, this.topRightCorner.getY() + moveY));
    }

    private void setArea(Coordinate lowLeftCorner, Coordinate topRightCorner){
        if (lowLeftCorner.getX() <= topRightCorner.getX() && lowLeftCorner.getY() >= topRightCorner.getY()){
            this.lowLeftCorner = lowLeftCorner;
            this.topRightCorner = topRightCorner;
        }
        else {
            //Kasta exception eller något käckt..
        }
    }
    
    
    /**
     * Method that check if the given Coordinate is located within the given Area.
     * @param coord The Coordinate that will be checked.
     * @param area The Area in which the Coordinate will be searched for.
     * @return <code>true</code> if the Coordinate is in the Area, otherwise <code>false</code>.
     */
    public static boolean coordinateInArea(Coordinate coord, Area area){
        if (coord.getX() < area.getLowLeftCorner().getX() || coord.getX() > area.getTopRightCorner().getX()){
            return false;
        }
        else if (coord.getY() > area.getLowLeftCorner().getY() || coord.getY() < area.getTopRightCorner().getY()){
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean areaInArea(Area collidingArea, Area checkArea){
        return true;
    }
}
