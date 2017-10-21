package br.edu.ifc.blumenau.woworlds.core.Patched;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

class Level1 extends ScreenAdapter {

    private TiledMap map;
    private OrthogonalTiledMapRenderer render;
    private OrthographicCamera camera;
    private Player player;
    private TiledMapTileLayer tileCollisionWalls;

    private Game game;

    Level1() {

        try {
            this.camera = new OrthographicCamera();
            this.camera.setToOrtho(false, 1280, 720);
            this.map = loadMap();
            this.player = new Player(Classe.INICIADOR);
            this.render = new OrthogonalTiledMapRenderer(map);
            this.render.setView(camera);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private TiledMap loadMap() throws Exception {
        try {
            return new TmxMapLoader().load("Mapas/Level1.tmx");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
