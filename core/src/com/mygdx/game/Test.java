package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Test implements Screen {
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private PlayerInGame player;
    
    Game game;
    
    public Test(Game game) {
        this.game = game;
        
    }
    
    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.1f);
        camera.zoom = 5f;
        player = new PlayerInGame(new Sprite(new Texture("player.jpg")));
    }
    
    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setY(player.getY() + (60 * 1.8f * delta));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setY(player.getY() - (60 * 1.8f * delta));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setX(player.getX() + (60 * 1.8f * delta));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setX(player.getX() - (60 * 1.8f * delta));
        }
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
        
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
    
}
