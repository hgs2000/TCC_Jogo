package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class Mapa extends ScreenAdapter {

    //Declaração variáveis
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    // <editor-fold defaultstate="collapsed" desc="Objetos relacionados ao jogador">
    private PlayerInGame player_sprite;
    private Animation<TextureRegion> player_anim;
    private Jogador jogador;
    private BitmapFont hpPlayer;
    private BitmapFont lvPlayer;

    /**
     * Como funciona o coisa abaixo:
     * <p>
     * [x - 32, y + 32][x, y + 32][x + 32, y + 32]
     * [x - 32, y]     [x, y]     [x + 32, y]
     * [x - 32, y - 32][x, y - 32][x + 32, y - 32]
     */
    private TiledMapTile[][] collisionArea = new TiledMapTile[3][3];

    //</editor-fold>

    //Objetos relacionados ao mapa
    private TiledMapTileLayer cLayer;
    private boolean[][] collision_map = {{false}};
    private String[][] collision_map_position = {{"null"}, {"null"}};
    //
    //private
    //


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
        this.cLayer = (TiledMapTileLayer) map.getLayers().get("Walls");
        this.collision_map = new boolean[this.cLayer.getWidth()][this.cLayer.getHeight()];
        //System.out.println(this.collision_map.length);
        //startCollisions();

    }

    private void startCollisions() {
        MapProperties keys;
        for (int y = 0; y < this.collision_map.length; y++) {
            for (int x = 0; x < this.collision_map[y].length; x++) {
                try {
                    keys = cLayer.getCell(x, y).getTile().getProperties();
                    System.out.println("test_solid");
                    //System.out.println(keys.get("solid"));
                    if ((boolean) (keys.get("solid")) == true) {
                        //System.out.println("yes");
                        this.collision_map[y][x] = true;
                    }
                } catch (NullPointerException ex) {
                    keys = null;
                    System.out.println("false");
                    ex.printStackTrace();
                }
                //collision_map_position[x][y] = "" + "";
            }
        }
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

        hpPlayer = new BitmapFont();
        hpPlayer.getData().setScale(1, 1);

        lvPlayer = new BitmapFont();
        lvPlayer.getData().setScale(1, 1);

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
        /*oldX = player.getX();
        oldY = player.getY();
        if ((Gdx.input.isKeyPressed(Input.Keys.U)) && (Gdx.input.isKeyPressed(Input.Keys.P))) {
            jogador.setVida(100000);
        }
        if (game.isMoveUp()) {
            player_sprite.setY(player_sprite.getY() + jogador.getVelocidade());
            //directY = true;
        }
        if (game.isMoveDown()) {
            player_sprite.setY(player_sprite.getY() - jogador.getVelocidade());
            //directY = false;
        }
        if (game.isMoveRight()) {
            player_sprite.setX(player_sprite.getX() + jogador.getVelocidade());
            //directX = true;
        }
        if (game.isMoveLeft()) {
            player_sprite.setX(player_sprite.getX() - jogador.getVelocidade());
            //directX = false;
        }
        try {
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
                    //cY = cLayer.getCell((int) ((player.getX() + player.getWidth()) / tW), (int) (player.getY() / tH)).getTile().getProperties().containsKey("solid");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }*/

        //checkCollision(camera.position);
        collisionAreaUpdate(camera.position);


        if (player_sprite != null) {
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
                inimigos.get(i).setPosition(100, 150);*/
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
            batch.end();
        } else {
            //System.out.println("Ok");
            Gdx.gl20.glClearColor(0, 0, 0, 1);

            state_time += Gdx.graphics.getDeltaTime();
            Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

            renderer.setView(camera);
            renderer.render();

            TextureRegion curPlayerFrame = jogador.getCurrentAnimation().getKeyFrame(state_time, true);
            Vector3 player_pos = new Vector3(Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2, 0);

            batch.begin();

            batch.draw(jogador.getCurrentAnimation().getKeyFrame(state_time, true), Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2, 75, 75);
            hpPlayer.draw(batch, "Hello World", 50, 50);
            player_anim.setPlayMode(Animation.PlayMode.REVERSED);
            batch.end();

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

    private void collisionAreaUpdate(Vector3 current_position) {

        int x = (int) current_position.x, y = (int) current_position.y;
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        try {
            collisionArea[0][0] = layer.getCell(x - 32, y + 32).getTile(); //1
            collisionArea[0][1] = layer.getCell(x, y + 32).getTile();//2
            collisionArea[0][2] = layer.getCell(x + 32, y + 32).getTile();//3
            collisionArea[1][0] = layer.getCell(x - 32, y).getTile();//4
            collisionArea[1][1] = layer.getCell(x, y).getTile();//5
            collisionArea[1][2] = layer.getCell(x + 32, y).getTile();//6
            collisionArea[2][0] = layer.getCell(x - 32, y - 32).getTile();//7
            collisionArea[2][1] = layer.getCell(x, y - 32).getTile();//8
            collisionArea[2][2] = layer.getCell(x + 32, y - 32).getTile();//9

            for (int linha = 0; linha < collisionArea.length; linha++) {
                for (int coluna = 0; coluna < collisionArea[linha].length; coluna++) {
                    if ((boolean) collisionArea[linha][coluna].getProperties().get("solid")) {
                        System.out.println("Chegou aqui (somehow)");
                    }
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("No no no");
        }
    }

    private void checkCollision(Vector3 current_position) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        int x = (int) current_position.x, y = (int) current_position.y;
        try {
            //if (layer.getCell(x+ 1, y).getTile().getProperties().get("solid"))


            System.out.println(x + ":" + y);
            TiledMapTileLayer.Cell cell = layer.getCell(x + 1, y);
            if ((Boolean) cell.getTile().getProperties().get("solid")) {
                System.out.println("Sake!");

            } else {
                System.out.println("Not Sake!");
            }
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
            System.out.println("Not Sake!");
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

    private boolean canMovePlayer(float delta) {
        boolean canUp = true, canDown = true, canLeft = true, canRight = true;
        boolean collisionBlock[][] = new boolean[3][3];

        if (player_sprite.getY() + (60 * 1.8f * delta) == 1) {
            System.out.println("Collision");
        }

        if (canUp && canDown && canLeft && canRight && false) {
            return false;
        }
        return true;
    }
}
