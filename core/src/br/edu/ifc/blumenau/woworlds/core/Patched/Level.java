package br.edu.ifc.blumenau.woworlds.core.Patched;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Level extends ScreenAdapter {

    private TiledMap map;
    TiledMapRenderer renderer;
    OrthographicCamera camera;
    protected TiledMapTileLayer cLayer;
    private Game game;
    protected Player player;
    SpriteBatch batch;
    float stateTime = 0;
    private Animation<TextureRegion> playerAnimation;

    Level(Game game, Player player, OrthographicCamera camera, int level) {
        this.game = game;
        this.player = player;
        //Start map
        this.map = new TmxMapLoader().load("Mapas/Level" + level + ".tmx");
        //Start camera
        this.camera = camera;
        this.camera.update();
        //Start renderer
        this.renderer = new OrthogonalTiledMapRenderer(this.map, 0.5f);
        this.renderer.setView(camera);
        //Start playeranimation
        changeAnimation();
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

    }

    private void changeAnimation() {
        playerAnimation = player.getCurrentAnimation();
    }
}
