package gamecore;

import java.util.LinkedList;

/**
 *
 * @author Linus
 */
public class Game {
    private int level;
    private int playerLives;
    private long lastupdate;
    private boolean isPaused, isStarted;
    private Map map;
    private LinkedList<Bullet> mobBullets = new LinkedList();
    private PlayerShip ship;
    private Window win;
    //private Score currentScore;

    public void startNewGame(){
        //start game somehow
        this.win = new Window(800, 600);
        this.map = new Map();
        this.map.fillMobGrid();
        this.gameloop();
        this.ship = new PlayerShip(/*Spawncoord, middle of screen?*/);
    }

    public void gameOver(){
        //save highscore
    }

    //Egen egen tråd för denna funktion och en annan för skeppet så att spelaren för sig oberoende av reseten av spelet?
    public void gameloop(){
        this.lastupdate = System.currentTimeMillis();

        while(!isPaused){
            long deltatime = System.currentTimeMillis() - this.lastupdate;
            this.lastupdate = System.currentTimeMillis();

            //Check for killed objects
            int i = 0;
            for (i = 0; i < mobBullets.size(); i++) {
                 if(Area.areaInArea(mobBullets.get(i).getArea(), ship.getArea())) {
                    //Bullet collide with plyership
                 }
            }

            if(ship.hasActiveProjectile()) {
                int j = 0;
                for (j = 0; j < map.getMobGrid()[j].length; j++) {
                    if(Area.areaInArea(/*Area player bullet*/map.getMobGrid()[j].getArea()))
                }
            }
            //if bullet has collided with object => kill object and the bullet
            //if bullet outside of screen => remove

            //move objects
             this.map.moveAllMobs(deltatime);
            //for(i=0;i < all active bullets;i++)
            //      bullets[i].moveObject(deltatime)



            //draw objects
            //for(i=0;i < all viewable objects;i++)
            //      ViewableObjects.paint(g)

        }
    }


    //Several more methods


}
