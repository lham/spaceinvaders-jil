package gamecore;

/**
 *
 * @author Linus
 */
public class Map {
    private int mobsAlive;
    private Mob mobGrid[][] = new Mob[5][11]; //[rows][columns]

    private int mostLeftColumn, mostRightColumn, lowestRow;
    private Direction mobDirection;
    private int playerBulletsFired;

    private int timeBuffert;

    /**
     * Constuctor
     */
    public Map(){
        this.mobsAlive = 0;
        this.mobDirection = Direction.RIGHT; //the direction the mobs move in when the game starts
        this.playerBulletsFired = 0;
        this.mostLeftColumn = 1;
        this.mostRightColumn = 11;
        this.lowestRow = 5;
    }



    /**
     * Create 55 new mobs
     */
    public void fillMobGrid(){
        //loop thru all positions
        for (int y = 0; y < this.mobGrid.length; y++) {
            for (int x = 0; x < this.mobGrid[y].length; x++) {
                //Create a mobs and send in the level (y-pos in the grid)
                this.mobGrid[y][x] = new Mob(this.mobGrid[y].length - y);

                //Add mob to mobsAlive
                this.mobsAlive++;
            }
        }
    }

    /**
     * Removes a mob from the mobGrid[y][x]
     * @param x x-pos
     * @param y y-pos
     */
    public void killMob(int x, int y){
        //Remove the mob
        this.mobGrid[y][x] = null;
        this.mobsAlive--;

        //Check if the extreme rows/columns/rows changed
        //row
        for (int yy = 0; yy < mobGrid.length; yy++) {

        }

    }

    public void moveAllMobs(long time){
        //loop thru all positions
        for (int y = 0; y < this.mobGrid.length; y++) {
            for (int x = 0; x < this.mobGrid[y].length; x++) {
                if(this.mobGrid[y][x] != null){
                    this.mobGrid[y][x].moveObject(this.mobDirection, time)
                }
            }
        }
    }

    public void increasePlayerBulletsFired() {
        this.playerBulletsFired++;
    }
    
    public int getLowestRow() {
        return lowestRow;
    }

    public int getMostLeftColumn() {
        return mostLeftColumn;
    }

    public int getMostRightColumn() {
        return mostRightColumn;
    }

    public int getPlayerBulletsFired() {
        return playerBulletsFired;
    }

    public int getMobsAlive(){
        return this.mobsAlive;
    }

    public int getTimeBuffert(){
        return this.timeBuffert;
    }


    
}
