package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class Mapa extends ScreenAdapter {

    //Declaração variáveis
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private PlayerInGame player;

    private Animation<TextureRegion> player_anim;
    private SpriteBatch batch;
    private float state_time = 0f;

    private int moveX = 0;
    private int moveY = 0;

    private TiledMapTileLayer cLayer;
    private float tW, tH;
    private Sprite hud;
    private Jogador ps;
    private BitmapFont hpPlayer;
    private BitmapFont lvPlayer;
    private TCC game;

    private String mapPath;
    private String playerImg;
    private int playerStartX;
    private int playerStartY;
    private ArrayList<Inimigo> inimigos;

    private float oldX, oldY;
    private boolean cX = false, cY = false, portal = false;
    private boolean directX, directY;//true +; false -
    //===========================


    public Mapa(Jogador ps, TCC game, String mapPath, String playerImg, int playerStartX, int playerStartY, ArrayList<Inimigo> inimigos) {
        this.ps = ps;
        this.game = game;
        this.map = new TmxMapLoader().load(mapPath);
        this.playerImg = playerImg;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.inimigos = inimigos;
        this.batch = new SpriteBatch();
    }

    public Mapa(Jogador ps, TCC game, String mapPath, Animation<TextureRegion> player, int playerStartX, int playerStartY, ArrayList<Inimigo> inimigos) {
        this.ps = ps;
        this.game = game;
        this.map = new TmxMapLoader().load(mapPath);
        this.playerImg = null;
        this.player_anim = player;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.inimigos = inimigos;
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {

        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2);
        //camera.zoom = 5f;

        if (player == null) {
            player = new PlayerInGame(new Sprite(new Texture(playerImg)));
            player.setPosition(playerStartX, playerStartY);
        }

        cLayer = (TiledMapTileLayer) map.getLayers().get(0);
        tW = cLayer.getTileWidth();
        tH = cLayer.getTileHeight();

        hud = new Sprite(new Texture("hud.png"));//HUD PADRAO HERE

        for (Inimigo ini : inimigos) {
            ini.setPosition(ini.x, ini.y);
        }

        hpPlayer = new BitmapFont();
        hpPlayer.getData().setScale(0.5f, 0.5f);

        lvPlayer = new BitmapFont();
        lvPlayer.getData().setScale(0.5f, 0.5f);

    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    @Override
    public void render(float delta) {
        oldX = player.getX();
        oldY = player.getY();
        if ((Gdx.input.isKeyPressed(Input.Keys.U)) && (Gdx.input.isKeyPressed(Input.Keys.P))) {
            ps.setVida(100000);
        }
        if (game.isMoveUp()) {
            player.setY(player.getY() + (60 * 1.8f * delta));
            directY = true;
        }
        if (game.isMoveDown()) {
            player.setY(player.getY() - (60 * 1.8f * delta));
            directY = false;
        }
        if (game.isMoveRight()) {
            player.setX(player.getX() + (60 * 1.8f * delta));
            directX = true;
        }
        if (game.isMoveLeft()) {
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
            TCC.currentMapPos += 1;
            game.setScreen(TCC.mapas.get(TCC.currentMapPos));
        }

        for (Inimigo ini : inimigos) {
            if (((ini.getX() - player.getX()) < 20) && ((ini.getX() - player.getX()) > -20)) {
                if (((ini.getY() - player.getY()) < 20) && ((ini.getY() - player.getY()) > -20)) {
                    ps.setVida(ps.getVida() - 1);
                }
            }
        }

        if (ps.getVida() <= 0) {
            game.setScreen(new Menu(game));
        }

        for (Inimigo ini : inimigos) {
            ini.move(player, delta, cLayer);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                if (((ini.getX() - player.getX()) < 50) && ((ini.getX() - player.getX()) > -50)) {
                    if (((ini.getY() - player.getY()) < 50) && ((ini.getY() - player.getY()) > -50)) {
                        ini.vida -= ps.getDano();
                    }
                }
            }
            if (ini.vida <= 0) {
                ini.vida = 5;
                ini.setPosition(100, 150);
                ps.addXP(1);
                ps.getCurrentLevel();
            }
        }

        //Muda animação
        if (game.isMoveDown() == true && game.isMoveUp() == true) {
            setMoveY(0);
        } else if (game.isMoveDown() == true && game.isMoveUp() == false) {
            setMoveY(-1);
        } else if (game.isMoveDown() == false && game.isMoveUp() == true) {
            setMoveY(1);
        } else {
            setMoveY(getMoveY() - getMoveY());
        }

        if (game.isMoveDown() == true && game.isMoveRight() == true) {
            setMoveX(0);
        } else if (game.isMoveLeft() == true && game.isMoveRight() == false) {
            setMoveX(-1);
        } else if (game.isMoveLeft() == false && game.isMoveRight() == true) {
            setMoveX(1);
        } else {
            setMoveX(getMoveX() - getMoveX());
        }
        setAnimation();
        //Fim muda animação

        camera.position.x = player.getX();
        camera.position.y = player.getY();

        camera.zoom = 0.75f;
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        for (Inimigo ini : inimigos) {
            ini.draw(renderer.getBatch());
        }

        TextureRegion curPlayerFrame = ps.getCurrentAnimation().getKeyFrame(state_time, true);

        renderer.getBatch().draw(curPlayerFrame, Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2, 75, 75);
        hpPlayer.draw(renderer.getBatch(), "Vida: " + Integer.toString(ps.getVida()), 10, 50);
        lvPlayer.draw(renderer.getBatch(), "Level" + Integer.toString(ps.getNivel()), 10, 100);
        player.draw(renderer.getBatch());
        hud.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    private void setAnimation() {
        if (getMoveX() > 0) {
            ps.setEstadoAtual(Jogador.EstadoJogador.WALK_RIGHT);
            return;
        } else if (getMoveX() < 0) {
            ps.setEstadoAtual(Jogador.EstadoJogador.WALK_LEFT);
            return;
        } else if (getMoveY() > 0) {
            ps.setEstadoAtual(Jogador.EstadoJogador.WALK_UP);
            return;
        } else if (getMoveY() < 0) {
            ps.setEstadoAtual(Jogador.EstadoJogador.WALK_DOWN);
            return;
        }
        switch (ps.getEstadoAtual()) {
            case WALK_RIGHT:
                ps.setEstadoAtual(Jogador.EstadoJogador.STOP_RIGHT);
                break;
            case WALK_LEFT:
                ps.setEstadoAtual(Jogador.EstadoJogador.STOP_LEFT);
                break;
            case WALK_UP:
                ps.setEstadoAtual(Jogador.EstadoJogador.STOP_UP);
                break;
            case WALK_DOWN:
                ps.setEstadoAtual(Jogador.EstadoJogador.STOP_DOWN);
                break;
        }
    }
}
