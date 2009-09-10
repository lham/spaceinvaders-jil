package gamecore;

/**
 *
 * @author Linus
 */
public class Map {
    private int mobsAlive;
    private Mob mobGrid[][];

    private int mostLeftColumn, mostRightColumn, lowestRow;
    private int playerBulletsFired;


    public Map(){
        //konstruktor
        //initera variabler osv
    }

    public int getMobsAlive(){
        //returnera mobsAlive
    }

    public void setMobsAlive(){
        //sätt mobsAlive
    }

    public void fillMobGrid(){
        //skapa ny mob i varje plats av arrayen
    }

    public void killMob(int x, int y){
        //sätt givna position i mobGrind[x][y] till null
    }

    


}
