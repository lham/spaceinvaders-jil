package gamecore;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import javax.swing.*;


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
    private String midScreenMsg = "";

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

        //Måla ut text
        g.setColor(Color.white);

        g.setFont(new Font("Courier New", Font.PLAIN, 20));
        g.drawString(Integer.toString(this.score), 5, 25);
        g.drawString(("Lives: " + this.playerLives), Game.width - g.getFontMetrics().stringWidth(("Lives: " + this.playerLives)) - 5, 25);

        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.drawString("Hit your spacebar to start!", (Game.width - g.getFontMetrics().stringWidth("Hit your spacebar to start!"))/2, Game.height-200);

        //Genomför alla utmålningar som sparats i g och ändra buffers
        g.dispose();
        this.strategy.show();

        //Pausa spelet och vänta på att spelaren ska starta genom keypress
        this.isPaused = true;
        
        while (true){
            if (!this.isPaused){
                this.gameloop();
            }

            if (this.input.firePressed()){
                this.isPaused = false;
            }

        }
    }


    public void gameloop(){
        this.lastupdate = System.currentTimeMillis();
        this.input.clearPresses();
        long msgTimeStamp = 0;

        while(this.playerLives > 0 && this.map.getMobsAlive() != 0){
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

            if (generator.nextInt(this.mobBulletFreq) == 0 && this.map.getMobsAlive() != 0){ //Bestäm om det skall spawna ett skott denna loopen
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
            for (int i = 0; i < this.mobBullets.size(); i++){
                if(Area.areaIntersectArea(this.mobBullets.get(i).getArea(), this.ship.getArea())){
                    //Ta bort alla bullets
                    this.playerBullet = null;
                    this.mobBullets.clear();

                    //Ta bort ett liv
                    if (--this.playerLives == 0){
                        this.midScreenMsg = "YOU LOST SUCKER!";
                        msgTimeStamp = System.currentTimeMillis();
                    }
                    else {
                        this.midScreenMsg = "YOU GOT HIT";
                        msgTimeStamp = System.currentTimeMillis();
                    }
                }
            }

            //PlayerBullet mot mobs
            if (this.playerBullet != null){
                for(int row = 0; row < this.map.getRows(); row++){
                    for (int col = 0; col < this.map.getColumns(); col++){
                        if (this.map.getMobGrid()[row][col] != null && this.playerBullet != null){
                            if (Area.areaIntersectArea(this.playerBullet.getArea(), this.map.getMobGrid()[row][col].getArea())){
                                this.score += this.map.killMob(row, col);
                                this.playerBullet = null;
                                this.midScreenMsg = "KILLSHOT";
                                msgTimeStamp = System.currentTimeMillis();
                            }
                        }
                    }
                }
                if (this.map.getMobsAlive() == 0){
                    this.midScreenMsg = "YOU WON! (moar lvls to come...)";
                }
            }





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

            //Måla ut top-text
            g.setColor(Color.white);
            g.setFont(new Font("Courier New", Font.PLAIN, 20));
            g.drawString(Integer.toString(this.score), 5, 25);
            g.drawString(("Lives: " + this.playerLives), Game.width - g.getFontMetrics().stringWidth(("Lives: " + this.playerLives)) - 5, 25);

            //Måla ut combat-text
            if (this.lastupdate - msgTimeStamp > 300){
                this.midScreenMsg = "";
            }

            
            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.drawString(this.midScreenMsg, (Game.width - g.getFontMetrics().stringWidth(this.midScreenMsg))/2, Game.height-200);


            //Genomför alla utmålningar som sparats i g och ändra buffers
            g.dispose();
            this.strategy.show();
            
        }

    }


    
}