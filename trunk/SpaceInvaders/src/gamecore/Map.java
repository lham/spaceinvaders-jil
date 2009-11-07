package gamecore;

import java.awt.Graphics2D;
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

    private int lowestRow;

    private Direction mobDirection;
    private double speed;

    /**
     * Constuctor
     */
    public Map(int level, String imagePath, double spaceMultiplier){
        //Sätt variabler
        this.mobsAlive = 0;
        this.mobDirection = Direction.RIGHT; //the direction the mobs move in when the game starts
        this.lowestRow = this.rows - 1;
        this.speed = level * 0.1;

        //Spawna alla mobs
        this.fillMobGrid(imagePath, spaceMultiplier);
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
                this.mobGrid[row][col] = new Mob((this.rows - row), new Coordinate((Game.width - spaceToFill)/2, 100), imagePath, this.speed);

                //Flytta mobben till dess position
                int x = col;
                int y = row;

                this.mobGrid[row][col].getArea().moveArea(
                        this.mobGrid[row][col].getSprite().getWidth()/2 + x*(this.mobGrid[row][col].getSprite().getWidth() + (spaceToFill - this.mobGrid[row][col].getSprite().getWidth()*this.columns)/10),
                        y*(this.mobGrid[row][col].getSprite().getHeight() + 20)
                );

                //Add mob to mobsAlive
                this.mobsAlive++;
            }
        }
    }

   
    private void updateLowestRow(){
            int killedColumns = 0;

            for(int col = 0; col < this.columns; col++){
                if (this.mobGrid[this.lowestRow][col] == null){
                    killedColumns++;
                }
            }

            if (killedColumns == this.columns){
                this.lowestRow--;
                this.updateLowestRow(); //Kör metoden igen så vi inte missar att uppdatera en gång till om raden ovanför är borta
            }
    }

    public int killMob(int killRow, int killCol){
        int value = this.mobGrid[killRow][killCol].getValue();

        //Remove the mob
        this.mobGrid[killRow][killCol] = null;
        this.mobsAlive--;

        
        //Kolla om vilken som är den lägsta raden
        if (killRow == this.lowestRow){
            this.updateLowestRow();
        }


        //uppdatera speed varje gång en mob dör
         for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if(this.mobGrid[row][col] != null){
                    this.mobGrid[row][col].setSpeed(this.speed * (1 + this.rows*this.columns/100.0 - this.mobsAlive/100.0));
                }
            }
         }




        //Returnera värdet på den mob vi tog bort
        return value;
    }
    
    public void paintAllMobs(Graphics2D g){
        for(int row = 0; row < this.rows; row++){
            for (int col = 0; col < this.columns; col++){
                if (this.mobGrid[row][col] != null){
                    this.mobGrid[row][col].drawObject(g);
                }
            }
        }
    }

    public void moveAllMobs(long time){
        boolean jumpDown = false;

        //Kolla om vi skall byta riktning
        searchSwap:
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if(this.mobGrid[row][col] != null){
                    //Kolla om moben har rört sig utanför skärmen
                    if(     this.mobGrid[row][col].getArea().getLowLeftCorner().getX()  - (int)(time*this.mobGrid[row][col].getSpeed()) <= 0 ||
                            this.mobGrid[row][col].getArea().getTopRightCorner().getX() + (int)(time*this.mobGrid[row][col].getSpeed()) >= Game.width){

                        //Byt riktning
                        this.mobDirection = this.mobDirection.invert();

                        //Hoppa ner ett steg
                        jumpDown = true;

                        //Hoppa ur sökningen
                        break searchSwap; //breakar ur båda looparna
                    }
                }
            }
        }

        //Flytta alla mobs
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if(this.mobGrid[row][col] != null){
                    this.mobGrid[row][col].moveObject(this.mobDirection, time);

                    if (jumpDown){
                        this.mobGrid[row][col].getArea().moveArea(0, 20);
                    }
                }
            }
        }
    }

    public int getMobsAlive(){
        return this.mobsAlive;
    }

    public Mob[][] getMobGrid(){
       return this.mobGrid;
    }

    public int getColumns(){
        return this.columns;
    }

    public int getRows(){
        return this.rows;
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

    public boolean mobsReachedBottom(int bottomY){
        for (int x = 0; x < this.columns; x++){
            if (this.mobGrid[this.lowestRow][x] != null){
                if (this.mobGrid[this.lowestRow][x].getArea().getLowLeftCorner().getY() >= bottomY){
                    return true;
                }
            }
        }

        return false;
    }
}
