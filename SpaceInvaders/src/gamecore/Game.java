package gamecore;

/**
 *
 * @author Linus
 */
public class Game {
    private int level;
    private int playerLives;
    private int lasttime;
    private boolean isPaused, isStarted;
    private Map map;
    //private Score currentScore;

    public void startNewGame(){
        //start game somehow
        this.map = new Map();
        this.map.fillMobGrid();
        this.gameloop();
    }

    public void gameOver(){
        //save highscore
    }

    //Egen egen tråd för denna funktion och en annan för skeppet så att spelaren för sig oberoende av reseten av spelet?
    public void gameloop(){
        //this.lastupdate = javapaket.getTimeStamp (millisekunder)

        while(true){


            if(this.isPaused){
               //sleep
            }
            else {
                //int deltatime = javapaket.getTimeStamp - this.lastupdate
                //this.lastupdate = javapaket.getTimeStamp

                //Check for killed objects
                //if bullet has collided with object => kill object and the bullet
                //if bullet outside of screen => remove

                //move objects
                //for(i=0;i < all mobs in grid;i++)
                //      this.map.moveAllMobs(deltatime)
                //for(i=0;i < all active bullets;i++)
                //      bullets[i].moveObject(deltatime)



                //draw objects
                //for(i=0;i < all viewable objects;i++)
                //      ViewableObjects.paint(g)

            }
        }


    }


    //Several more methods


}
