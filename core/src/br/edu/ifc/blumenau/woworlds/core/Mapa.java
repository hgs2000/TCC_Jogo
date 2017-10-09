package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.util.ArrayList;

public class Mapa implements Screen {

    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private PlayerInGame player;
    private TiledMapTileLayer cLayer;
    private float tW, tH;
    private Sprite hud;
    private Inimigo ini;
    private Player ps;
    private BitmapFont hpIni;
    private BitmapFont hpPlayer;
    private BitmapFont lvPlayer;
    Game game;
    
    //of the the
    private String mapPath;
    private String playerImg;
    private int playerStartX;
    private int playerStartY;
    private ArrayList<Inimigo> inimigos;
    //===========================

    public Mapa(Player ps, Game game, String mapPath, String playerImg, int playerStartX, int playerStartY, ArrayList<Inimigo> inimigos) {
        this.ps = ps;
        this.game = game;
        this.mapPath = mapPath;
        this.playerImg = playerImg;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.inimigos = inimigos;
    }
    
    
    

    @Override
    public void show() {
        map = new TmxMapLoader().load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2);
        camera.zoom = 5f;
        player = new PlayerInGame(new Sprite(new Texture(playerImg)));
        player.setPosition(playerStartX, playerStartY);
        cLayer = (TiledMapTileLayer) map.getLayers().get(0);
        tW = cLayer.getTileWidth();
        tH = cLayer.getTileHeight();
        hud = new Sprite(new Texture("hud.png"));//HUD PADRAO HERE
        
         ini = new Inimigo(new Sprite(new Texture("ske.png")));
        ini.setPosition(100, 150);
        
        hpPlayer = new BitmapFont();
        hpPlayer.getData().setScale(0.5f, 0.5f);
        
        lvPlayer = new BitmapFont();
        lvPlayer.getData().setScale(0.5f, 0.5f);
    }
    
    private float oldX, oldY;
    boolean cX = false, cY = false, portal = false;
    boolean directX, directY;//true +; false -
    
    
    

    @Override
    public void render(float delta) {
        oldX = player.getX();
        oldY = player.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.U)) && (Gdx.input.isKeyPressed(Input.Keys.P))) {
            ps.life = 100000;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setY(player.getY() + (60 * 1.8f * delta));
            directY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setY(player.getY() - (60 * 1.8f * delta));
            directY = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setX(player.getX() + (60 * 1.8f * delta));
            directX = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setX(player.getX() - (60 * 1.8f * delta));
            directX = false;
        }
        
        //colisão
        //top left
        if (!directX) {

            cX = cLayer.getCell((int) (player.getX() / tW), (int) ((player.getY() + player.getHeight()) / tW)).getTile().getProperties().containsKey("solid");
            portal = cLayer.getCell((int) (player.getX() / tW), (int) ((player.getY() + player.getHeight()) / tW)).getTile().getProperties().containsKey("portal");

            //meio left
            if (!cX) {
                cX = cLayer.getCell((int) (player.getX() / tW), (int) ((player.getY() + player.getHeight() / 2) / tH)).getTile().getProperties().containsKey("solid");
            }
            //baixo left
            if (!cX) {
                cX = cLayer.getCell((int) (player.getX() / tW), (int) (player.getY() / tH)).getTile().getProperties().containsKey("solid");
            }
        } else if (directX) {
            //top right
            cX = cLayer.getCell((int) ((player.getX() + player.getWidth()) / tW), (int) ((player.getY() + player.getHeight()) / tH)).getTile().getProperties().containsKey("solid");
            //meio right
            if (!cX) {
                cX = cLayer.getCell((int) ((player.getX() + player.getWidth()) / tW), (int) ((player.getY() + player.getWidth() / 2) / tH)).getTile().getProperties().containsKey("solid");
            }
            //baixo right
            if (!cX) {
                cX = cLayer.getCell((int) ((player.getX() + player.getWidth()) / tW), (int) ((player.getY()) / tW)).getTile().getProperties().containsKey("solid");
            }
        }
        if (!directY) {
            cY = cLayer.getCell((int) (player.getX() / tW), (int) (player.getY() / tH)).getTile().getProperties().containsKey("solid");
            if (!cY) {
                cY = cLayer.getCell((int) ((player.getX() + player.getWidth() / 2) / tW), (int) (player.getY() / tH)).getTile().getProperties().containsKey("solid");
            }

            if (!cY) {
                cY = cLayer.getCell((int) ((player.getX() + player.getWidth()) / tW), (int) (player.getY() / tH)).getTile().getProperties().containsKey("solid");
            }

        } else if (directY) {
            cY = cLayer.getCell((int) (player.getX() / tW), (int) ((player.getY() + player.getHeight()) / tH)).getTile().getProperties().containsKey("solid");

            if (!cY) {
                cY = cLayer.getCell((int) ((player.getX() + player.getWidth() / 2) / tW), (int) ((player.getY() + player.getHeight()) / tH)).getTile().getProperties().containsKey("solid");
            }
            if (!cY) {
                cY = cLayer.getCell((int) ((player.getX() + player.getWidth()) / tW), (int) ((player.getY() + player.getHeight()) / tH)).getTile().getProperties().containsKey("solid");
            }
        }

        //end colisão
        if (cX) {
            cX = false;
            player.setX(oldX);
        }
        if (cY) {
            cY = false;
            player.setY(oldY);
        }
        if (portal) {
            game.setScreen(new test2(game, ps));
        }
        if (((ini.getX() - player.getX()) < 20) && ((ini.getX() - player.getX()) > -20)) {
            if (((ini.getY() - player.getY()) < 20) && ((ini.getY() - player.getY()) > -20)) {
                ps.life -= 1;
            }
        }
        if (ps.life <= 0) {
            player.setPosition(400, 150);
            ps.life = 100;
            ps.lv -= 1;
        }
        
        ini.move(player, delta, cLayer);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (((ini.getX() - player.getX()) < 50) && ((ini.getX() - player.getX()) > -50)) {
                if (((ini.getY() - player.getY()) < 50) && ((ini.getY() - player.getY()) > -50)) {
                    ini.vida -= ps.dano;
                }
            }
        }
        
        if (ini.vida <= 0) {
            ini.vida = 5;
            ini.setPosition(100, 150);
            ps.addXp(1);
            ps.lvCheck();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        ini.draw(renderer.getBatch());
        hpPlayer.draw(renderer.getBatch(), "Vida: " + Integer.toString(ps.life), 10, 50);
        lvPlayer.draw(renderer.getBatch(), "Level" + Integer.toString(ps.lv), 10, 100);
        player.draw(renderer.getBatch());
        hud.draw(renderer.getBatch());
        renderer.getBatch().end();
        
    }

    @Override
    public void resize(int i, int i1) {

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
