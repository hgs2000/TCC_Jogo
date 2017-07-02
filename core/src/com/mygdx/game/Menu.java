package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen {

    private SpriteBatch batch;
    Texture title;
    Texture newGame, loadGame, options, exit;
    Music menuMusic;
    private TCC game;


    public Menu(final TCC game) {
        this.game = game;
        
        batch = new SpriteBatch();
        title = new Texture("titulo.png");
        newGame = new Texture("newGame.png");
        loadGame = new Texture("loadGame.png");
        options = new Texture("options.png");
        exit = new Texture("exit.png");
        if (TCC.op) {
            menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
            menuMusic.setLooping(true);
            menuMusic.setVolume(0.5f);
            menuMusic.play();
            TCC.op = false;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //(() && ()) && (() && ())
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            int h = Gdx.graphics.getHeight();
            int w = Gdx.graphics.getWidth();
            if (((x > 44) && (x < 346)) && ((y < h - 450) && (y > h - (500)))) {
                //evento quando clica no botão newGame
            }
            if (((x > 44) && (x < 346)) && ((y < h - 380) && (y > h - (430)))) {
                //evento quando clica no botão loadGame
            }
            if (((x > 44) && (x < 346)) && ((y < h - 310) && (y > h - (360)))) {
                //evento quando clica no botão options
                game.setScreen(new Options(game));
            }
            if (((x > 44) && (x < 346)) && ((y < h - 240) && (y > h - (290)))) {
                //evento quando clica no botão exit
                System.exit(0);
            }

        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(title, 30, Gdx.graphics.getHeight() - 100);
        batch.draw(newGame, 45, Gdx.graphics.getHeight() - 250);
        batch.draw(loadGame, 45, Gdx.graphics.getHeight() - 320);
        batch.draw(options, 45, Gdx.graphics.getHeight() - 390);
        batch.draw(exit, 45, Gdx.graphics.getHeight() - 460);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
