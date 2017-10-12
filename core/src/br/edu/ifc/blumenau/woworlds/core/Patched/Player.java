package br.edu.ifc.blumenau.woworlds.core.Patched;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Classe utilizada pelo jogo para representar o jogador
 */
public class Player {

    private Classe classe = Classe.INICIADOR;

    private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Animations/Knight_Movement.atlas"));

    private Animation walkingup;
    private Animation walkingdown;
    private Animation walkingleft;
    private Animation walkingright;

    /**
     * @param classe pode ser MAGO, CAVALEIRO ou ASSASSINO
     */
    public Player(String classe) {

        try {
            this.classe = Classe.valueOf(classe);
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    private Animation loadAnimation() {

        float elapsedTime = 0;

        //Concluir daqui
        //Animation anim = new Animation(0.33f, );

        return null;

    }

}

enum Classe {
    INICIADOR, MAGO, CAVALEIRO, ASSASSINO
}
