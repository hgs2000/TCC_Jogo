package scene2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jogo extends Game {

    private SpriteBatch batch;
    private ITrocaTela trocaTela;

    @Override
    public void create() {
        batch = new SpriteBatch();
        trocaTela = new TrocaTela(screen);
    }

    @Override
    public void render() {
        float r = 0 / 255f;
        float g = 0 / 255f;
        float b = 0 / 255f;
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Screen proxima = trocaTela.getProximaTela();
        if (proxima != getScreen()) {
            setScreen(proxima);
        }
        super.render();
    }

}
