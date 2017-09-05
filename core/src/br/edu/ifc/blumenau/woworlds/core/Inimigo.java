package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Inimigo extends Sprite {

    int vida = 5;

    public void move(Sprite player, float delta) {

        if (player.getX() > this.getX()) {
            this.setX(this.getX() + (60 * 0.5f * delta));
        }
        if (player.getY() > this.getY()) {
            this.setY(this.getY() + (60 * 0.5f * delta));
        }
        if (player.getX() < this.getX()) {
            this.setX(this.getX() - (60 * 0.5f * delta));
        }
        if (player.getY() < this.getY()) {
            this.setY(this.getY() - (60 * 0.5f * delta));
        }
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
