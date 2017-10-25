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

    public Animation<TextureRegion> getPlayerAnimation() {
        return playerAnimation;
    }

    Level(MainGame game, Player player, OrthographicCamera camera, int level) {
        this.game = game;
        this.player = player;
        //Start map
        this.map = new TmxMapLoader().load("Mapas/Level" + level + ".tmx");
        Vector2 spawnPos = new Vector2(247, 74);
        try {
            TiledMapTileLayer layer = (TiledMapTileLayer) this.map.getLayers().get("Floor");
            spawnPos.set(layer.getCell(14, 14).getTile().getOffsetX(), layer.getCell(14, 14).getTile().getOffsetY());
            throw new Exception("NotFound");

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        System.out.println("Spawn: " + spawnPos.x + "," + spawnPos.y);

        //Start camera
        this.camera = camera;
        this.camera.position.set(spawnPos, 0);
        this.camera.update();
        //Start renderer
        this.renderer = new OrthogonalTiledMapRenderer(this.map, 0.5f);
        this.renderer.setView(camera);
        //Start playeranimation
        this.playerAnimation = player.getCurrentAnimation();
        this.playerAnimation.setPlayMode(Animation.PlayMode.LOOP);
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    private void changeAnimation() {
        playerAnimation = player.getCurrentAnimation();
    }
}
