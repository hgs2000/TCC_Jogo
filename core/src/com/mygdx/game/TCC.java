package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TCC extends Game {
    
    
    SaveGame save;
    static int tela = 0;
    static boolean op = true;
    Texture test;
    @Override
    public void create() {
        this.setScreen(new Menu(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
