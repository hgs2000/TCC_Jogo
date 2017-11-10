package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class TCC extends Game {

    static ArrayList<Mapa> mapas = new ArrayList<Mapa>();
    static int currentMapPos = -1;
    SaveGame save;
    static boolean op = true;

    private Jogador jogador;
    private Animation<TextureRegion> player_animation;

    private boolean moveUp = false, moveDown = false, moveLeft = false, moveRight = false;

    @Override
    public void create() {
        jogador = new Jogador();
        player_animation = jogador.getCurrentAnimation();
        ArrayList<Inimigo> ini = new ArrayList();
        ini.add(new Inimigo(new Sprite(new Texture(Gdx.files.internal("ske.png"))), 150, 200));
        //mapas.add(new Mapa(jogador, this, "Mapas/Level1.tmx", "player.png", 200, 150, ini));
        mapas.add(new Mapa(jogador, this, "Mapas/Level1.tmx", player_animation, 200, 150, ini));

        this.setScreen(new Menu(this));
        //this.setScreen(new Level());
        Gdx.input.setInputProcessor(new InputProcessor(this));
    }

    //<editor-fold defaultstate="collapsed" desc="Movimentação">
    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
    //</editor-fold>

}
