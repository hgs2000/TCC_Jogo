package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.Random;

public class Inimigo extends Sprite {

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    int vida = 20;
    int x, y;
    boolean directX, directY;
    float oldX = this.getX();
    float oldY = this.getY();
    boolean cX, cY;
    float tW, tH;
    boolean traped = false;
    int passos = -1;
    Random gen = new Random();
    String imgPath;

    public void move(Sprite player, float delta, TiledMapTileLayer cLayer) {

        if (traped) {
            switch (passos) {
                case (0):
                    this.setY(this.getY() + (60 * 2f * delta));
                    break;
                case (1):
                    this.setY(this.getY() - (60 * 2f * delta));
                    break;
                case (2):
                    this.setX(this.getX() + (60 * 2f * delta));
                    break;
                case (3):
                    this.setX(this.getX() - (60 * 2f * delta));
                    break;
            }
            traped = false;
        }

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
        if (gen.nextBoolean()) {
            this.setY(this.getY() - (60 * (gen.nextInt(5) / 10f) * delta));
        } else {
            this.setY(this.getY() - (60 * (gen.nextInt(5) / 10f) * delta));
        }
        //if(gen.nextBoolean()){
        //   this.setX(this.getX() - (60 * (gen.nextInt(10) / 10f) * delta));
        //}else{
        //    this.setX(this.getX() - (60 * (gen.nextInt(10) / 10f) * delta));
        //}

        //colisão
        //top left
        /*if (!directX) {

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
         */
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
        if ((this.getX() == oldX) && (this.getY() == oldY)) {
            traped = true;
            passos += 1;
        } else {
            passos = -1;
        }
    }

    public Inimigo(Sprite sprite, int x, int y) {
        super(sprite);
        this.x = x;
        this.y = y;

    }

    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void Dano() {
        vida -= 1;
    }

}
