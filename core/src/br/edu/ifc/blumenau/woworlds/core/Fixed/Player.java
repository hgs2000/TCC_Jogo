package br.edu.ifc.blumenau.woworlds.core.Fixed;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player {

    private Classe classe;

    private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Animations/Knight_Movement.atlas"));

    private Animation walkingup;
    private Animation walkingdown;
    private Animation walkingleft;
    private Animation walkingright;

    /**
     * 
     * @param classe pode ser MAGO, CAVALEIRO ou ASSASSINO
     */
    public Player(String classe) {

        this.classe = Classe.valueOf(classe);
        
    }
    
    private Animation loadAnimation(){
        
        //Concluir daqui
        Animation anim = new Animation;
        
        return null;
        
    }

}

enum Classe {
        MAGO, CAVALEIRO, ASSASSINO
    }
