/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author migue
 */
public class Options extends ScreenAdapter {

    private final SpriteBatch BATCH;
    Texture options, menu;
    private final TCC GAME;

    public Options(final TCC Game) {
        this.GAME = Game;
        BATCH = new SpriteBatch();
        options = new Texture("options.png");
        menu = new Texture("menu.png");
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //(() && ()) && (() && ())
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            int h = Gdx.graphics.getHeight();
            int w = Gdx.graphics.getWidth();
            if (((x > 44) && (x < 346)) && ((y < h - 170) && (y > h - (220)))) {
                //evento quando clica no botão menu
                GAME.setScreen(new Menu(GAME));
            }

        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BATCH.begin();
        BATCH.draw(options, 30, Gdx.graphics.getHeight() - 100);
        BATCH.draw(menu, 45, Gdx.graphics.getHeight() - 530);
        BATCH.end();
    }

}
