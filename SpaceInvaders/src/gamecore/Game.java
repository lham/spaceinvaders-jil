package gamecore;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game {
    public static void main(String[] args){
        Game coolboy = new Game();
    }

    //Fönsterrelaterade variabler
    private Canvas gamearea;
    private JFrame window;
    private InputHandler input = new InputHandler();
    private BufferStrategy strategy;
    
    static public int height = 600;
    static public int width = 800;
    
    //Spelvariabler
    private int level;
    private int playerLives;
    private long lastupdate;
    private boolean isPaused;
    private Map map;
    private LinkedList<Bullet> mobBullets = new LinkedList();
    private Bullet playerBullet = null;
    private PlayerShip ship;
    private int score;

    public Game (){
        //Skapa fönstret (det faktiska fönstren, ramen osv.)
        this.window = new JFrame("Stargate Invaders by Snail, Stravik and the noob");

        //Skapa Canvasen allting skall ritas upp på
        this.gamearea = new Canvas();
        this.gamearea.setBounds(0, 0, Game.width, Game.height);
        this.gamearea.setIgnoreRepaint(true);

        //Hämta window:s contentPane så vi kan lägga till vår canvas på denna (genom att använda JPanel)
        JPanel setupPanel = (JPanel) this.window.getContentPane();
        setupPanel.setPreferredSize(new Dimension(Game.width, Game.height));
        setupPanel.setLayout(null);
        setupPanel.add(this.gamearea);

        //Packa ihop allting och starta
        this.window.pack();
        this.window.setResizable(false);
        this.window.setVisible(true);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Koppla KeyListener till vår frame så vi kan ta emot keyboard input
        this.gamearea.addKeyListener(this.input);
        this.gamearea.requestFocus();

        //Hantera den accelererade grafiken!
        this.gamearea.createBufferStrategy(2);
        this.strategy = this.gamearea.getBufferStrategy();

        //Initiera spelet :D
        this.startNewGame();
    }


    public void startNewGame(){
        //Reseta knappar
        this.input.clearPresses();

        //Skapa ett PlayerShip
        this.ship = new PlayerShip(new Coordinate(Game.width/2, Game.height - 20), "deadlus.png");
        this.ship.getArea().moveArea(-(this.ship.getSprite().getWidth()/2), 0);

        //Skapa alla mobs
        this.map = new Map();
        this.map.fillMobGrid("al'kesh.png", 0.6);

        //Skapa en g2d
        Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,Game.width,Game.height);

        //Måla ut skeppet
        this.ship.drawObject(g);

        //Måla ut mobsen
        for(int row = 0; row < this.map.getRows(); row++){
            for (int col = 0; col < this.map.getColumns(); col++){
                if (this.map.getMobGrid()[row][col] != null){
                    this.map.getMobGrid()[row][col].drawObject(g);
                }
            }
        }

        //Genomför alla utmålningar som sparats i g och ändra buffers
        g.dispose();
        this.strategy.show();

        //Pausa spelet och KÖR
        this.isPaused = false;
        this.gameloop();
    }


    public void gameloop(){
        this.lastupdate = System.currentTimeMillis();

        while(!isPaused){
            long deltatime = System.currentTimeMillis() - this.lastupdate;
            this.lastupdate = System.currentTimeMillis();

            //------------------------------------------------------------------
            //Utför operationer utifrån spelarens input
            //------------------------------------------------------------------

            //Skeppförflyttning
            if((this.input.leftPressed()) && (!this.input.rightPressed())) {
                this.ship.moveObject(Direction.LEFT, deltatime);
            }
            else if((!this.input.leftPressed()) && (this.input.rightPressed())) {
                this.ship.moveObject(Direction.RIGHT, deltatime);
            }

            //Försöker skjuta
            if (this.input.firePressed() && !this.ship.hasActiveProjectile()){
                this.playerBullet = 
                        new Bullet(
                            Direction.UP,
                            new Coordinate(
                                this.ship.getArea().getTopRightCorner().getX() - this.ship.getSprite().getWidth()/2,
                                this.ship.getArea().getTopLeftCorner().getY() - 5),
                            "drone.png"
                        );
                this.playerBullet.getArea().moveArea(-this.playerBullet.getSprite().getWidth()/2, 0);
                this.ship.setActiveProjectile(true);
            }



            
            //Check for killed objects
//            int i = 0;
//            for (i = 0; i < mobBullets.size(); i++) {
//                 if(Area.areaInArea(mobBullets.get(i).getArea(), ship.getArea())) {
//                    //Bullet collide with plyership
//                 }
//            }
//
//            if(ship.hasActiveProjectile()) {
//                int j = 0;
//                for (j = 0; j < map.getMobGrid()[j].length; j++) {
//  //                  if(Area.areaInArea(/*Area player bullet*/map.getMobGrid()[j].getArea())) {
//
//                }
//            }
            

            
            //if bullet has collided with object => kill object and the bullet
            //if bullet outside of screen => remove

            //Move objects
            //------------------------------------------------------------------
//             this.map.moveAllMobs(deltatime);
            if (this.playerBullet != null){
                this.playerBullet.moveObject(Direction.UP, deltatime);
            }

            //for(i=0;i < all active bullets;i++)
            //      bullets[i].moveObject(deltatime)



            //Rita upp alla objekt
            //------------------------------------------------------------------
            //Skapa en g2d
            Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0,0,Game.width,Game.height);

            //Måla ut skeppet
            this.ship.drawObject(g);

            //Måla ut eventuella Bullets
            if (this.playerBullet != null){
                this.playerBullet.drawObject(g);
            }

            for (int i = 0; i < this.mobBullets.size(); i++) {
                this.mobBullets.get(i).drawObject(g);
            }

            //Måla ut mobsen
            for(int row = 0; row < this.map.getRows(); row++){
                for (int col = 0; col < this.map.getColumns(); col++){
                    if (this.map.getMobGrid()[row][col] != null){
                        this.map.getMobGrid()[row][col].drawObject(g);
                    }
                }
            }

            //Genomför alla utmålningar som sparats i g och ändra buffers
            g.dispose();
            this.strategy.show();
            
        }
    }


    
}