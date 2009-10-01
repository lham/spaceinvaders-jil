package gamecore;

/**
 *
 * @author Linus
 */
public class Game {
    private int level;
    private int playerLives;
    private boolean isPaused, isStarted;
    private Map map;
    //private Score currentScore;

    public void startNewGame(){
        //start game somehow
        map = new Map();
        map.fillMobGrid();

    }

    public void gameOver(){
        //save highscore
    }

    //Several more methods


}
