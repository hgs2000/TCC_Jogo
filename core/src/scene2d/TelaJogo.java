package scene2d;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class TelaJogo {

    //Continuar base : https://github.com/RoaringCatGames/libgdx-ashley-box2d-example
    private boolean iniciada = false;
    private float tempoAtual = 0f;

    private World mundo;
    private PooledEngine engine;

    private SpriteBatch batch;
    private TrocaTela troca;

    public TelaJogo(SpriteBatch sb, TrocaTela tt) {
        super();
        this.batch = sb;
        this.troca = tt;
    }

    private void init() {
        Gdx.app.log("Tela", "Inicializando");
        iniciada = true;

        engine = new PooledEngine();
        Rendering
    }

}
