package com.mygdx.game;
import com.badlogic.gdx.Game;

public class TCC extends Game {
    
    
    SaveGame save;
    static boolean op = true;
    @Override
    public void create() {
        this.setScreen(new Menu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
