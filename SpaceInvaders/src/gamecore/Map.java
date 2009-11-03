package gamecore;

import java.util.Random;

/**
 *
 * @author Linus
 */
public class Map {
    private int mobsAlive;
    private int rows = 5;
    private int columns = 11;
    private Mob mobGrid[][] = new Mob[this.rows][this.columns]; //[rows][columns]

    private int mostLeftColumn, mostRightColumn, lowestRow;

    private Direction mobDirection;
    private int speed; //px/sek


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
        this.speed = 10;
    }

    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }


    /**
     * Create 55 new mobs
     */
    public void fillMobGrid(String imagePath, double spaceMultiplier){
        int spaceToFill = (int) (Game.width * spaceMultiplier);
        
        //Loopa igenom hela griden
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {


                //Skapa en mob i mitten, 100 pixlar neråt
                this.mobGrid[row][col] = new Mob((this.rows - row), new Coordinate((Game.width - spaceToFill)/2, 100), imagePath);

                //Flytta mobben till dess position
                int x = col;
                int y = row;

                this.mobGrid[row][col].getArea().moveArea(
                /* x*(mobWidth+padding) */  x*(this.mobGrid[row][col].getSprite().getWidth() + ((spaceToFill) - (this.mobGrid[row][col].getSprite().getWidth() * this.columns)) / 10),
                /* y*(mobHeight+padding) */ y*(this.mobGrid[row][col].getSprite().getHeight() + 20)
                        );

                //Add mob to mobsAlive
                this.mobsAlive++;
            }
        }
    }

    /**
     * Removes a mob from the mobGrid[y][x]
     * @param x x-pos
     * @param y y-pos3
     * @return Points value of the removed mob
     */
    public int killMob(int killRow, int killCol){
        int value = this.mobGrid[killRow][killCol].getValue();

        //Remove the mob
        this.mobGrid[killRow][killCol] = null;
        this.mobsAlive--;

        return value;


        //Check if the extreme rows/columns/rows changed
        

    }

    public void moveAllMobs(long time){
//        //loop thru all positions
//        for (int y = 0; y < this.mobGrid.length; y++) {
//            for (int x = 0; x < this.mobGrid[y].length; x++) {
//                if(this.mobGrid[y][x] != null){
//                    if(this.mobGrid[this.mostRightColumn][x].getArea().getTopRightCorner().getX() > 800){ //800 = totalWidth
//                        this.mobDirection = this.mobDirection.invert();
//
//                        //hoppa ner ett steg
//
//                    }
//                    else if(this.mobGrid[this.mostLeftColumn][x].getArea().getTopRightCorner().getX() < 0){
//                        this.mobDirection = this.mobDirection.invert();
//                        //Hoppa ner ett steg
//                    }
//
//                    this.mobGrid[y][x].moveObject(this.mobDirection, time);
//                }
//            }
//        }
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

    public Mob[][] getMobGrid(){
       return this.mobGrid;
    }

    public int[] getRandomPosition(){
        while (true){
            Random generator = new Random();

            int row = generator.nextInt(this.rows);
            int col = generator.nextInt(this.columns);

            if (this.mobGrid[row][col] != null){
                return new int[] {row, col};
            }
        }
    }
}
