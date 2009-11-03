package gamecore;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;
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

    //Spelobject
    private Map map;
    private LinkedList<Bullet> mobBullets = new LinkedList();
    private Bullet playerBullet = null;
    private PlayerShip ship;

    //Spelvariabler
    private int level;
    private int playerLives;
    private long lastupdate;
    private boolean isPaused;
    private int score;
    private int mobBulletFreq;

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

        //Sätt spelvariabler
        this.playerLives = 3;
        this.score = 0;
        this.level = 1;
        this.mobBulletFreq = 3000;

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

        //Pausa spelet och vänta på att spelaren ska starta genom keypress
        this.isPaused = false;
        
        while (!this.isPaused){
            this.gameloop();
        }
    }


    public void gameloop(){
        this.lastupdate = System.currentTimeMillis();

        while(this.playerLives > 0){
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
            if (this.input.firePressed() && this.playerBullet == null){
                this.playerBullet = new Bullet(
                            new Coordinate(
                                this.ship.getArea().getTopRightCorner().getX() - this.ship.getSprite().getWidth()/2,
                                this.ship.getArea().getTopLeftCorner().getY() - 5
                            ),
                            "drone.png"
                        );

                this.playerBullet.getArea().moveArea(-this.playerBullet.getSprite().getWidth()/2, 0);

            }



            //------------------------------------------------------------------
            //Utför datorstyrda operationer
            //------------------------------------------------------------------

            //Flytta mobsen
            this.map.moveAllMobs(deltatime);

            //Flytta mobBullets
            for (int i = 0; i < this.mobBullets.size(); i++){
                this.mobBullets.get(i).moveObject(Direction.DOWN, deltatime);
            }

            //Flytta playerBullet
            if (this.playerBullet != null){
                this.playerBullet.moveObject(Direction.UP, deltatime);
            }

            //Spawna random mobBullet
            Random generator = new Random();

            if (generator.nextInt(this.mobBulletFreq) == 0){ //Bestäm om det skall spawna ett skott denna loopen
                int[] where = this.map.getRandomPosition(); //row, col
                this.mobBullets.add(new Bullet(this.map.getMobGrid()[where[0]][where[1]].getArea().getLowLeftCorner(), "mob.png"));
            }



            
            //------------------------------------------------------------------
            //Ta bort objekt som kolliderat
            //------------------------------------------------------------------

            //MobBullets mot kant
            for (int i = 0; i < this.mobBullets.size(); i++){
                if (this.mobBullets.get(i).getArea().getLowLeftCorner().getY() >= Game.width){
                    this.mobBullets.remove(i);
                }
            }

            //PlayerBullet mot kant
            if (this.playerBullet != null){
                if (this.playerBullet.getArea().getTopRightCorner().getY() <= 0){
                    this.playerBullet = null;
                }
            }

            //MobBullets mot skepp
//            for (int i = 0; i < this.mobBullets.size(); i++){
//                //BEEP YOU LOST A LIFE :DD
//            }

            //PlayerBullet mot mobs




            //------------------------------------------------------------------
            //Rita upp alla objekt
            //------------------------------------------------------------------

            //Skapa en g2d
            Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0,0,Game.width,Game.height);

            //Måla ut skeppet
            this.ship.drawObject(g);

            //Måla ut eventuella Bullets
            for (int i = 0; i < this.mobBullets.size(); i++) {
                this.mobBullets.get(i).drawObject(g);
            }

            if (this.playerBullet != null){
                this.playerBullet.drawObject(g);
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