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

    //Spelobjekt
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
    private CombatText ct = new CombatText();

    private int framecount;
    private long fpsStamp;
    private int fps;

    public Game (){
        //Skapa fönstret (det faktiska fönstren, ramen osv.)
        this.window = new JFrame("Stargate Invaders by Snail, Stravik and Jon");

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
        this.mobBulletFreq = 500/this.level; //Kontrollerar hur ofta skott skall spawna ifrån mobsen
        this.ct.addMessage("Hit your spacebar to start!",true);

        //Skapa ett PlayerShip & mobs
        this.ship = new PlayerShip(new Coordinate(Game.width/2, Game.height - 20), "deadlus.png");
        this.map = new Map(this.level, "al'kesh.png", 0.6);

        //Måla ut allting
        this.paintObjects();

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
        this.fpsStamp = System.currentTimeMillis();
        this.input.clearPresses();
        this.ct.clearMessage();

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

            //Spawna random mobBullet, topskepp, (items?)
            this.spawnSpecialObjects();
            



            
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
                    //Ta bort alla bullets om man blir träffad
                    this.playerBullet = null;
                    this.mobBullets.clear();

                    //Ta bort ett liv
                    if (--this.playerLives == 0)
                        this.ct.addMessage("YOU LOST SUCKER!",false);
                    else 
                        this.ct.addMessage("YOU GOT HIT!",false);
                    
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
                                this.ct.addMessage("KILLSHOT!",false);
                            }
                        }
                    }
                }
                if (this.map.getMobsAlive() == 0){
                    this.ct.addMessage("YOU WON! (moar lvls to come...)",true);
                }
            }

            //Mobs mot PlayerShip
            if (this.map.mobsReachedBottom(this.ship.getArea().getTopRightCorner().getY())){
                this.ct.addMessage("YOU LOST (SHOOT TO KILL NOOB)", true);
                this.playerLives = 0;
            }


            //------------------------------------------------------------------
            //Rita upp alla objekt
            //------------------------------------------------------------------
            this.paintObjects();

            //Manage fps
            try { Thread.sleep(10); } catch (Exception e) {}
            this.framecount++;

            if (this.lastupdate - this.fpsStamp >= 1000){
                this.fps = this.framecount;
                this.framecount = 0;
                this.fpsStamp = System.currentTimeMillis();
            }
            
        }

    }

    public void paintObjects(){
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
        this.map.paintAllMobs(g);

        //Måla ut top-text
        g.setColor(Color.white);
        g.setFont(new Font("Courier New", Font.PLAIN, 20));
        g.drawString(("Score: " + this.score), 5, 25);
        g.drawString(("Lives: " + this.playerLives), Game.width - g.getFontMetrics().stringWidth(("Lives: " + this.playerLives)) - 5, 25);

        g.drawString(("FPS: " + this.fps), 5, Game.height - 25);


        //Måla combat text
        this.ct.drawCombatText(g, Game.height - 200);

        //Genomför alla utmålningar som sparats i g och ändra buffers
        g.dispose();
        this.strategy.show();
    }
    
    public void spawnSpecialObjects(){
        //Spawna random mob bullet
        Random generator = new Random();
        if (generator.nextInt(this.mobBulletFreq) == 0 && this.map.getMobsAlive() != 0){ //Bestäm om det skall spawna ett skott denna loopen
            int[] pos = this.map.getRandomPosition(); //row, col

            Coordinate spawnCoord = new Coordinate(
                this.map.getMobGrid()[pos[0]][pos[1]].getArea().getLowRightCorner().getX() - this.map.getMobGrid()[pos[0]][pos[1]].getSprite().getWidth()/2,
                this.map.getMobGrid()[pos[0]][pos[1]].getArea().getLowRightCorner().getY() - this.map.getMobGrid()[pos[0]][pos[1]].getSprite().getHeight()
            );

            this.mobBullets.add(new Bullet(spawnCoord, "mob.png"));
        }

        //Spawna topskepp
        //todo..
    }


    
}