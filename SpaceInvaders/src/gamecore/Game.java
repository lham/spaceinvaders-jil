package gamecore;

import java.util.LinkedList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private int score;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean firePressed = false;
    //private Score currentScore;

    public void startNewGame(){
        //start game somehow
        this.win = new Window(800, 600);
        this.map = new Map();
        this.map.fillMobGrid();
        this.gameloop();
        this.ship = new PlayerShip(new Coordinate(400, 575));
        addKeyListener(new InputHandler());
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
                    if(Area.areaInArea(/*Area player bullet*/map.getMobGrid()[j].getArea())) {
                        
                    }
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

public int getScore(){
    return score;
}
    //Several more methods

private class InputHandler extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            firePressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 27) {
            System.exit(0);
        }
    }
}
}


