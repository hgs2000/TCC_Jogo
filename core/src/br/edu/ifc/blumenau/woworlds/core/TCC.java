package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Game;
import java.util.ArrayList;

public class TCC extends Game {

    
    static ArrayList<Mapa> mapas = new ArrayList<Mapa>();
    static int actual = 0;
    SaveGame save;
    static boolean op = true;

    @Override
    public void create() {
        
        
        
        this.setScreen(new Menu(this));
    }

}
