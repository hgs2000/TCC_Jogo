package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

public class TCC extends Game {

    static ArrayList<Mapa> mapas = new ArrayList<Mapa>();
    static int currentMapPos = -1;
    SaveGame save;
    static boolean op = true;

    @Override
    public void create() {
        ArrayList<Inimigo> ini = new ArrayList<Inimigo>();
        ini.add(new Inimigo(new Sprite(new Texture(Gdx.files.internal("ske.png"))), 150, 200));
        mapas.add(new Mapa(new Player(), this, "map.tmx", "player.png", 200, 150, ini));
        this.setScreen(new Menu(this));
    }

}
