package gamecore;

/**
 * Area contains information about a square area of pixels on the screen. The
 * area must have 4 corners which each are 90 degrees.
 * @author Linus
 */
public class Area {
    Coordinate lowLeftCorner, topRightCorner;

    public Area (Coordinate lowLeftCorner, Coordinate topRightCorner){
        if (lowLeftCorner.getX() <= topRightCorner.getX() && lowLeftCorner.getY() <= topRightCorner.getY()){
            this.lowLeftCorner = lowLeftCorner;
            this.topRightCorner = topRightCorner;
        }
        else {
            //felmeddelande eller exception...
        }
    }

    /**
     * Returns the lenght of the bottom or top of the area.
     * @return The lenght of the base of the area.
     */
    public int getLenght(){
        return this.topRightCorner.getX() - this.lowLeftCorner.getX();
    }

    /**
     * Returns the height of the right or left side of the area.
     * @return The height of the side of the area.
     */
    public int getHeight(){
        return this.topRightCorner.getY() - this.lowLeftCorner.getY();
    }


    public Coordinate getLowLeftCorner(){
        return this.lowLeftCorner;
    }
    public Coordinate getLowRightCorner(){
        return new Coordinate(this.topRightCorner.getX(), this.lowLeftCorner.getY());
    }
    public Coordinate getTopRightCorner(){
        return this.topRightCorner;
    }
    public Coordinate getTopLeftCorner(){
        return new Coordinate(this.lowLeftCorner.getX(), this.topRightCorner.getY());
    }

    //Gör setmetoder för ovan + javadoc

    
    
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
        else if (coord.getY() < area.getLowLeftCorner().getY() || coord.getY() > area.getTopRightCorner().getY()){
            return false;
        }
        else {
            return true;
        }
    }
}
