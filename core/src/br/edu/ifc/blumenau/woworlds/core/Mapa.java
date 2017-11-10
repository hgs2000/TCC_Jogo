package br.edu.ifc.blumenau.woworlds.core;

//<editor-fold defaultstate="collapsed" desc="Importações">
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
//</editor-fold>

public class Mapa extends ScreenAdapter {

    //<editor-fold defaultstate="collapsed" desc="Declaração variáveis">
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    // <editor-fold defaultstate="collapsed" desc="Objetos relacionados ao jogador">
    private PlayerInGame player_sprite;
    private Animation<TextureRegion> player_anim;
    private Jogador jogador;
    private Vector3 player_pos;
    private TextureRegion curPlayerFrame;

    /**
     * Como funciona o coisa abaixo:
     * <p>
     * [x - 32, y + 32][x, y + 32][x + 32, y + 32] [x - 32, y] [x, y] [x + 32,
     * y] [x - 32, y - 32][x, y - 32][x + 32, y - 32]
     */
    private TiledMapTile[][] collisionArea = new TiledMapTile[3][3];

    private BitmapFont texto16;
    private BitmapFont texto32;

    //</editor-fold>
    //<editor-fold desc="Objetos relacionados ao mapa"> 
    private TiledMapTileLayer collision_layer;

    private SpriteBatch batch;
    private float state_time = 0f;

    private int moveX = 0;
    private int moveY = 0;

    //private float tW, tH;
    private Sprite hud;
    private TCC game;

    //private String mapPath;
    //private String playerImg;
    private int playerStartX;
    private int playerStartY;
    private ArrayList<Inimigo> inimigos;

    private float oldX, oldY;
    private boolean cX = false, cY = false, portal = false;
    //private boolean directX, directY;//true +; false -
    //===========================
    //</editor-fold>

    public Mapa(Jogador ps, TCC game, String mapPath, String playerImg, int playerStartX, int playerStartY, ArrayList<Inimigo> inimigos) {
        this.jogador = ps;
        this.game = game;
        this.map = new TmxMapLoader().load(mapPath);
        //this.playerImg = playerImg;
        this.player_sprite = new PlayerInGame(new Sprite(new Texture(playerImg)));
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.inimigos = inimigos;
        this.batch = new SpriteBatch();
        this.collision_layer = (TiledMapTileLayer) map.getLayers().get("Walls");
        //System.out.println(this.collision_map.length);
        //startCollisions();

    }

    public Mapa(Jogador ps, TCC game, String mapPath, Animation<TextureRegion> player, int playerStartX, int playerStartY, ArrayList<Inimigo> inimigos) {
        this.jogador = ps;
        this.game = game;
        this.map = new TmxMapLoader().load(mapPath);
        this.player_sprite = null;
        this.player_anim = player;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.inimigos = inimigos;
        this.batch = new SpriteBatch();
        this.collision_layer = (TiledMapTileLayer) map.getLayers().get(0);
        //startCollisions();
    }

    @Override
    public void show() {
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2);
        //camera.zoom = 5f;

        if (player_sprite != null) {

            player_sprite.setPosition(playerStartX, playerStartY);
        }

        //tW = cLayer.getTileWidth();
        //tH = cLayer.getTileHeight();
        hud = new Sprite(new Texture("hud.png"));//HUD PADRÃO HERE

        for (Inimigo ini : inimigos) {
            ini.setPosition(ini.x, ini.y);
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Millennium-Regular_0.ttf"));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        parameter.magFilter = Texture.TextureFilter.Nearest;
        parameter.size = 16;

        texto16 = generator.generateFont(parameter);
        texto16.getData().setScale(1);

        parameter.size = 32;

        texto32 = generator.generateFont(parameter);
        texto32.getData().setScale(1);

        generator.dispose();

        curPlayerFrame = jogador.getCurrentAnimation().getKeyFrame(state_time, true);
        player_pos = new Vector3(Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2, 0);

        /*for (int x = 0; x < collision_layer.getWidth(); x++) {
            System.out.println("InicioColuna " + x);
            for (int y = 0; y < collision_layer.getHeight(); y++) {
                System.out.println("InicioLinha " + y);
                System.out.println(collision_layer.getCell(x, y).getTile().getProperties());
            }
        }*/
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters MoveX e MoveY">
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
//</editor-fold>

    @Override
    public void render(float delta) {
        if ((Gdx.input.isKeyPressed(Input.Keys.U)) && (Gdx.input.isKeyPressed(Input.Keys.P))) {
            jogador.setVida(100000);
        }

        if (portal) {
            TCC.currentMapPos += 1;
            game.setScreen(TCC.mapas.get(TCC.currentMapPos));
        }

        //checkCollision(camera.position);
        if (player_sprite != null) {
            /*
            for (Inimigo ini : inimigos) {
                if (((ini.getX() - player_sprite.getX()) < 20) && ((ini.getX() - player_sprite.getX()) > -20)) {
                    if (((ini.getY() - player_sprite.getY()) < 20) && ((ini.getY() - player_sprite.getY()) > -20)) {
                        jogador.setVida(jogador.getVida() - 1);
                    }
                }
            }

            if (jogador.getVida() <= 0) {
                game.setScreen(new Menu(game));
            }

            int contInis = inimigos.size();
            for (int i = 0; i < inimigos.size(); i++) {
                inimigos.get(i).move(player_sprite, delta, cLayer);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    if (((inimigos.get(i).getX() - player_sprite.getX()) < 50) && ((inimigos.get(i).getX() - player_sprite.getX()) > -50)) {
                        if (((inimigos.get(i).getY() - player_sprite.getY()) < 50) && ((inimigos.get(i).getY() - player_sprite.getY()) > -50)) {
                            inimigos.get(i).vida -= jogador.getDano();
                        }
                    }
                }
                if (inimigos.get(i).vida <= 0) {
                    inimigos.remove(i);
                    /*inimigos.get(i).vida = 5;
                inimigos.get(i).setPosition(100, 150);
                    jogador.addXP(1);
                    jogador.getCurrentLevel();
                }
            }

            //<editor-fold defaultstate="collapsed" desc="Muda animação">
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
            //Fim muda animação </editor-fold>

            //System.out.println(delta);
            camera.position.x += getMoveX();
            camera.position.y += getMoveY();

            camera.zoom = 0.75f;
            camera.update();

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            renderer.setView(camera);
            renderer.render();

            batch.begin();
            for (Inimigo ini : inimigos) {
                ini.draw(batch);
            }
            player_anim = jogador.getCurrentAnimation();
            //System.out.println("Estado atual: " + ps.getEstadoAtual());

            batch.draw(player_anim.getKeyFrame(state_time), Gdx.graphics.getWidth() / 2 - player_anim.getKeyFrame(state_time).getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - player_anim.getKeyFrame(state_time).getRegionHeight() / 2, 75, 75);
            hpPlayer.draw(batch, "Vida: " + Integer.toString(jogador.getVida()), 10, 50);
            lvPlayer.draw(batch, "Level" + Integer.toString(jogador.getNivel()), 10, 100);
            player_sprite.draw(batch);
            hud.draw(batch);
            batch.end();*/
        } else {
            //System.out.println("Ok");
            Gdx.gl20.glClearColor(0, 0, 0, 1);

            state_time += Gdx.graphics.getDeltaTime();
            Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

            renderer.setView(camera);
            renderer.render();

            curPlayerFrame = jogador.getCurrentAnimation().getKeyFrame(state_time, true);

            player_pos.x = Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2;
            player_pos.y = Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2;

            //canMovePlayer(delta);
            batch.begin();
            Vector2 centro_tela = new Vector2(Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2);
            Vector2 tamanho_personagens = new Vector2(75, 75);
            batch.draw(jogador.getCurrentAnimation().getKeyFrame(state_time, true), centro_tela.x, centro_tela.y, 75, 75);

            texto16.draw(batch, "Hello World", 50, 100);
            texto32.draw(batch, "XP atual: ", 50, 50);
            player_anim.setPlayMode(Animation.PlayMode.REVERSED);
            batch.end();

            boolean wallUp = getColisao(1);
            //boolean wallLeft = getCollisao(2);
            //boolean wallDown = getCollisao(3);
            //boolean wallRight = getCollisao(4);
            if (game.isMoveDown() == true && game.isMoveUp() == true) {
                setMoveY(0);
            } else if (game.isMoveDown() == true && game.isMoveUp() == false) {
                setMoveY(-1);
            } else if (game.isMoveDown() == false && game.isMoveUp() == true) {
                setMoveY(1);
            } else {
                setMoveY(getMoveY() - getMoveY());
            }

            if (game.isMoveLeft() == true && game.isMoveRight() == true) {
                setMoveX(0);
            } else if (game.isMoveLeft() == true && game.isMoveRight() == false) {
                setMoveX(-1);
            } else if (game.isMoveLeft() == false && game.isMoveRight() == true) {
                setMoveX(1);
            } else {
                setMoveX(getMoveX() - getMoveX());
            }

            setAnimation();
            camera.translate(getMoveX(), getMoveY());
            camera.update();
        }

    }

    private void setAnimation() {
        if (getMoveX() > 0) {
            jogador.setEstadoAtual(Jogador.EstadoJogador.WALK_RIGHT);
            return;
        } else if (getMoveX() < 0) {
            jogador.setEstadoAtual(Jogador.EstadoJogador.WALK_LEFT);
            return;
        } else if (getMoveY() > 0) {
            jogador.setEstadoAtual(Jogador.EstadoJogador.WALK_UP);
            return;
        } else if (getMoveY() < 0) {
            jogador.setEstadoAtual(Jogador.EstadoJogador.WALK_DOWN);
            return;
        }
        switch (jogador.getEstadoAtual()) {
            case WALK_RIGHT:
                jogador.setEstadoAtual(Jogador.EstadoJogador.STOP_RIGHT);
                break;
            case WALK_LEFT:
                jogador.setEstadoAtual(Jogador.EstadoJogador.STOP_LEFT);
                break;
            case WALK_UP:
                jogador.setEstadoAtual(Jogador.EstadoJogador.STOP_UP);
                break;
            case WALK_DOWN:
                jogador.setEstadoAtual(Jogador.EstadoJogador.STOP_DOWN);
                break;
        }
    }

    private boolean getColisao(int i) {

        TiledMapTile next;
        try {
            switch (i) {
                case 1:
                //next = (collision_layer).getCell((int) (player_pos.x + moveX), (int) player_pos.y).getTile();
                //System.out.println(next.getProperties().get("solid", Boolean.class));
                case 2:
                case 3:
                case 4:
                default:
                    return false;
            }

            //next = collision_layer.getCell((int) (player_pos.x + jogador.getVelocidade()), (int) player_pos.y).getTile();}
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
