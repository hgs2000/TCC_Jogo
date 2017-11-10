package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Level extends ScreenAdapter {

    private TCC jogo;
    private Camera camera;
    //<editor-fold desc="Variáveis de Mapa">
    private TiledMap mapa;

    private World world;
    //</editor-fold>Mapa

    //Jogador
    Body jogador_body;

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public Level() {
        System.out.println("Operação não suportada");
    }

    public Level(TCC game, String url) {
        Box2D.init();
        this.jogo = game;
        this.mapa = new TmxMapLoader().load(url);
        this.world = new World(new Vector2(0, 0), true);
        this.camera = new OrthographicCamera();
    }

    @Override
    public void render(float delta) {
        debugRenderer.render(world, camera.combined);
    }
}
