/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.blumenau.woworlds.core.testes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 *
 * @author tads
 */
public class Player extends Actor {

    private final Sprite player_sprite;
    Texture texture = new Texture("player.png");
    float actorX = 0, actorY = 0;
    public boolean started = false;

    public Player() {
        this.player_sprite = new Sprite(texture);
        this.player_sprite.setPosition(25, 50);
        setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((Player) event.getTarget()).started = true;
                return true;
            }

        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, actorX, actorY);
    }

    @Override
    public void act(float delta) {
        if (started) {
            actorX += 5;
        }
    }
    private Stage stage;

    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Player player = new Player();
        player.setTouchable(Touchable.enabled);
        stage.addActor(player);
    }

    public void dispose() {
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

}
