package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends ScreenAdapter {

    private final SpriteBatch BATCH;
    private Texture title;
    private Texture new_game, load_game, options, exit;
    private final TCC GAME;

    Menu(final TCC game) {
        this.GAME = game;

        BATCH = new SpriteBatch();
        title = new Texture("titulo.png");
        new_game = new Texture("newGame.png");
        load_game = new Texture("loadGame.png");
        options = new Texture("options.png");
        exit = new Texture("exit.png");
        if (TCC.op) {
            Music menu_music = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
            menu_music.setLooping(true);
            menu_music.setVolume(0.5f);
            menu_music.play();
            TCC.op = false;
        }
        //Player playerStatus = new Player();

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
                TCC.currentMapPos = 0;

                GAME.setScreen(TCC.mapas.get(TCC.currentMapPos));
            }
            if (((x > 44) && (x < 346)) && ((y < h - 380) && (y > h - (430)))) {
                //evento quando clica no botão loadGame
                System.out.println("To be completed");
            }
            if (((x > 44) && (x < 346)) && ((y < h - 310) && (y > h - (360)))) {
                //evento quando clica no botão options
                GAME.setScreen(new Options(GAME));
            }
            if (((x > 44) && (x < 346)) && ((y < h - 240) && (y > h - (290)))) {
                //evento quando clica no botão exit
                System.exit(0);
            }
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BATCH.begin();
        BATCH.draw(title, 30, Gdx.graphics.getHeight() - 100);
        BATCH.draw(new_game, 45, Gdx.graphics.getHeight() - 250);
        BATCH.draw(load_game, 45, Gdx.graphics.getHeight() - 320);
        BATCH.draw(options, 45, Gdx.graphics.getHeight() - 390);
        BATCH.draw(exit, 45, Gdx.graphics.getHeight() - 460);
        BATCH.end();
    }

}
