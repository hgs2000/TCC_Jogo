package br.edu.ifc.blumenau.woworlds.core.Patched;

import br.edu.ifc.blumenau.woworlds.core.Patched.Bases.MainGame;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Level extends ScreenAdapter {

    TiledMap map;
    TiledMapRenderer renderer;
    OrthographicCamera camera;
    protected TiledMapTileLayer cLayer;

    private int moveX, moveY;

    private MainGame game;
    protected Player player;
    SpriteBatch batch;
    float stateTime = 0;
    private Animation<TextureRegion> playerAnimation;


    private MapObjects collision;

    public Animation<TextureRegion> getPlayerAnimation() {
        return playerAnimation;
    }

    Level(MainGame game, Player player, OrthographicCamera camera, int level) {
        this.game = game;
        this.player = player;
        //Start map
        this.map = new TmxMapLoader().load("Mapas/Level" + level + ".tmx");
        Vector2 spawnPos = new Vector2(244, 68);
        TiledMapTileLayer layer = (TiledMapTileLayer) this.map.getLayers().get(0);
        Vector2 size = new Vector2(layer.getWidth(), layer.getHeight());

        //System.out.println("Spawn: " + spawnPos.x + "," + spawnPos.y);

        //Start camera
        this.camera = camera;
        this.camera.position.set(spawnPos, 0);
        this.camera.update();

        //Start renderer
        this.renderer = new OrthogonalTiledMapRenderer(this.map, 0.5f);
        this.renderer.setView(camera);

        //Start as colisões do mapa
        this.collision = map.getLayers().get(1).getObjects();

        //Start playeranimation
        this.playerAnimation = player.getCurrentAnimation();
        this.playerAnimation.setPlayMode(Animation.PlayMode.LOOP);

        this.batch = new SpriteBatch();
    }

    MainGame getGame() {
        return game;
    }

    public void setGame(MainGame game) {
        this.game = game;
    }

    int getMoveX() {
        return moveX;
    }

    void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    int getMoveY() {
        return moveY;
    }

    void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    void changeAnimation() {
        playerAnimation = player.getCurrentAnimation();
    }

    public MapObjects getCollision() {
        return collision;
    }

    public void setCollision(MapObjects collision) {
        this.collision = collision;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
