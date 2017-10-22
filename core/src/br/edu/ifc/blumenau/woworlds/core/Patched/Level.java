package br.edu.ifc.blumenau.woworlds.core.Patched;


import br.edu.ifc.blumenau.woworlds.core.Patched.Bases.MainGame;
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

    TiledMap map;
    TiledMapRenderer renderer;
    OrthographicCamera camera;
    protected TiledMapTileLayer cLayer;

    private MainGame game;
    protected Player player;
    SpriteBatch batch;
    float stateTime = 0;
    private Animation<TextureRegion> playerAnimation;

    Level(MainGame game, Player player, OrthographicCamera camera, int level) {
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

    public MainGame getGame() {
        return game;
    }

    public void setGame(MainGame game) {
        this.game = game;
    }

    private void changeAnimation() {
        playerAnimation = player.getCurrentAnimation();
    }
}
