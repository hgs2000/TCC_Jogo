package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Game;

public class TCC extends Game {

    SaveGame save;
    static boolean op = true;

    @Override
    public void create() {
        this.setScreen(new Menu(this));
    }

}
