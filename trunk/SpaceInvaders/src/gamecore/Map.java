package gamecore;

/**
 *
 * @author Linus
 */
public class Map {
    private int mobsAlive;
    private Mob mobGrid[][] = new Mob[10][4];

    private int mostLeftColumn, mostRightColumn, lowestRow;
    private int mobDirection;
    private int playerBulletsFired;


    public Map(){
        //konstruktor
        //initera variabler osv
    }



    public void fillMobGrid(){
        //skapa ny mob i varje plats av arrayen
        for (int y = 0; y < mobGrid.length; y++) {
            for (int x = 0; x < mobGrid[y].length; x++) {
                mobGrid[x][y] = new Mob(y);
            }
        }
    }

    public void killMob(int x, int y){
        //sätt givna position i mobGrind[x][y] till null
    }

    public void setLowestRow(int lowestRow) {
        this.lowestRow = lowestRow;
    }

    public void setMobsAlive(int mobsAlive) {
        this.mobsAlive = mobsAlive;
    }

    public void setMostLeftColumn(int mostLeftColumn) {
        this.mostLeftColumn = mostLeftColumn;
    }

    public void setMostRightColumn(int mostRightColumn) {
        this.mostRightColumn = mostRightColumn;
    }

    public void setPlayerBulletsFired(int playerBulletsFired) {
        this.playerBulletsFired = playerBulletsFired;
    }

    public void setMobsAlive(){
        //sätt mobsAlive
    }
    
    public int getLowestRow() {
        return lowestRow;
    }

    public int getMostLeftColumn() {
        return mostLeftColumn;
    }

    public int getMostRightColumn() {
        return mostRightColumn;
    }

    public int getPlayerBulletsFired() {
        return playerBulletsFired;
    }

    public int getMobsAlive(){
        return this.mobsAlive;
    }
    
}
