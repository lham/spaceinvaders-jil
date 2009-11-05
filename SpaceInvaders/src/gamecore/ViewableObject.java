
package gamecore;

import java.awt.Graphics2D;

public abstract class ViewableObject {
    private Area area;
    private Sprite sprite;
    
    public ViewableObject(Coordinate spawnCoord, String imagePath){
        //Ladda in spriten
        this.sprite = SpriteStore.get().getSprite(imagePath);

        //Spara arean
        this.area = new Area(
                new Coordinate (
                    spawnCoord.getX(),
                    spawnCoord.getY()
                ),
                new Coordinate(
                    spawnCoord.getX() + this.sprite.getWidth(),
                    spawnCoord.getY() - this.sprite.getHeight()
                )
            );

        //Flytta arean i x-led så den blir centrerad över spawnCoordinaten
        this.area.moveArea(-this.sprite.getWidth()/2, 0);

    }

    public void drawObject(Graphics2D g){
        this.sprite.draw(g, this.area.getTopLeftCorner().getX(), this.area.getTopLeftCorner().getY());
        
    }

    public Area getArea(){
        return this.area;
    }

    public Sprite getSprite(){
        return this.sprite;
    }

}

