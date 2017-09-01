package br.edu.ifc.blumenau.woworlds.core.testes;

import br.edu.ifc.blumenau.woworlds.core.Test;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen extends ScreenAdapter {

    final WorldOfWorlds jogo;

    OrthographicCamera camera;

    public MainMenuScreen(final WorldOfWorlds jogo) {
        this.jogo = jogo;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        jogo.batch.setProjectionMatrix(camera.combined);
        jogo.batch.begin();
        jogo.font.getData().setScale(1.5f);
        jogo.font.draw(jogo.batch, "World of Worlds", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        jogo.font.draw(jogo.batch, "Presione para iniciar", 100, 100);
        jogo.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            jogo.setScreen(new Test(jogo));
        }
    }

}
