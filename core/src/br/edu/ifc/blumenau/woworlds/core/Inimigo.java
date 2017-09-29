package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Inimigo extends Sprite {

    int vida = 5;

    boolean directX, directY;
    float oldX = this.getX();
    float oldY = this.getY();
    boolean cX, cY;
    float tW, tH;
    
    
    public void move(Sprite player, float delta, TiledMapTileLayer cLayer) {

        tW = this.getWidth();
        tH = this.getHeight();
        
        
        
        if (player.getX() > this.getX()) {
            this.setX(this.getX() + (60 * 0.5f * delta));
            directX = true;
        }
        if (player.getY() > this.getY()) {
            this.setY(this.getY() + (60 * 0.5f * delta));
            directY = true;
        }
        if (player.getX() < this.getX()) {
            this.setX(this.getX() - (60 * 0.5f * delta));
            directX = false;
        }
        if (player.getY() < this.getY()) {
            this.setY(this.getY() - (60 * 0.5f * delta));
            directY = false;
        }
        
        //colisão
        //top left
        if (!directX) {

            cX = cLayer.getCell((int) (this.getX() / tW), (int) ((this.getY() + this.getHeight()) / tW)).getTile().getProperties().containsKey("solid");
            

            //meio left
            if (!cX) {
                cX = cLayer.getCell((int) (this.getX() / tW), (int) ((this.getY() + this.getHeight() / 2) / tH)).getTile().getProperties().containsKey("solid");
            }
            //baixo left
            if (!cX) {
                cX = cLayer.getCell((int) (this.getX() / tW), (int) (this.getY() / tH)).getTile().getProperties().containsKey("solid");
            }
        } else if (directX) {
            //top right
            cX = cLayer.getCell((int) ((this.getX() + this.getWidth()) / tW), (int) ((this.getY() + this.getHeight()) / tH)).getTile().getProperties().containsKey("solid");
            //meio right
            if (!cX) {
                cX = cLayer.getCell((int) ((this.getX() + this.getWidth()) / tW), (int) ((this.getY() + this.getWidth() / 2) / tH)).getTile().getProperties().containsKey("solid");
            }
            //baixo right
            if (!cX) {
                cX = cLayer.getCell((int) ((this.getX() + this.getWidth()) / tW), (int) ((this.getY()) / tW)).getTile().getProperties().containsKey("solid");
            }
        }
        if (!directY) {
            cY = cLayer.getCell((int) (this.getX() / tW), (int) (this.getY() / tH)).getTile().getProperties().containsKey("solid");
            if (!cY) {
                cY = cLayer.getCell((int) ((this.getX() + this.getWidth() / 2) / tW), (int) (this.getY() / tH)).getTile().getProperties().containsKey("solid");
            }

            if (!cY) {
                cY = cLayer.getCell((int) ((this.getX() + this.getWidth()) / tW), (int) (this.getY() / tH)).getTile().getProperties().containsKey("solid");
            }

        } else if (directY) {
            cY = cLayer.getCell((int) (this.getX() / tW), (int) ((this.getY() + this.getHeight()) / tH)).getTile().getProperties().containsKey("solid");

            if (!cY) {
                cY = cLayer.getCell((int) ((this.getX() + this.getWidth() / 2) / tW), (int) ((this.getY() + this.getHeight()) / tH)).getTile().getProperties().containsKey("solid");
            }
            if (!cY) {
                cY = cLayer.getCell((int) ((this.getX() + this.getWidth()) / tW), (int) ((this.getY() + this.getHeight()) / tH)).getTile().getProperties().containsKey("solid");
            }
        }

        //end colisão
        if (cX) {
            cX = false;
            this.setX(oldX);
        }
        if (cY) {
            cY = false;
            this.setY(oldY);
        }
        oldX = this.getX();
        oldY = this.getY();
        
        
    }

    public Inimigo(Sprite sprite) {
        super(sprite);

    }

    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void Dano() {
        vida -= 1;
    }

}
