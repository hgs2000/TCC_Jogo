/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author migue
 */
public class Options implements Screen {

    private SpriteBatch batch;
    Texture options, menu;
    private TCC game;

    public Options(final TCC Game) {
        this.game = Game;
        batch = new SpriteBatch();
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
                game.setScreen(new Menu(game));
            }

        }
        
        
        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(options, 30, Gdx.graphics.getHeight() - 100);
        batch.draw(menu, 45, Gdx.graphics.getHeight() - 530);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void show() {

    }

}
