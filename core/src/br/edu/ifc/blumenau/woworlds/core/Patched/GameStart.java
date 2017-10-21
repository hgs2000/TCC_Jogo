package br.edu.ifc.blumenau.woworlds.core.Patched;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;

public class GameStart extends Game {
    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {
        this.setScreen(new Level1());
    }
}
