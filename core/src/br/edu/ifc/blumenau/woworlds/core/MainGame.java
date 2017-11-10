package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGame extends Game {

    private Stage stage;
    private OrthographicCamera camera;

    private boolean moveUp = false;
    private boolean moveLeft = false;
    private boolean moveDown = false;
    private boolean moveRight = false;

    public boolean getMoveUp() {
        return moveUp;
    }

    public boolean getMoveLeft() {
        return moveLeft;
    }

    public boolean getMoveDown() {
        return moveDown;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        stage = new Stage(new ScreenViewport(camera), new SpriteBatch());
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                String pressed = "";
                switch (keycode) {
                    case Input.Keys.A:
                        pressed = "Left Pressed;";
                        moveLeft = true;
                        break;
                    case Input.Keys.D:
                        pressed = "Right Pressed;";
                        moveRight = true;
                        break;
                    case Input.Keys.W:
                        pressed = "Up Pressed;";
                        moveUp = true;
                        break;
                    case Input.Keys.S:
                        pressed = "Down Pressed;";
                        moveDown = true;
                        break;
                    case Input.Keys.ESCAPE:
                        System.exit(0);
                        break;
                    default:
                        pressed = "All Released";
                }
                System.out.println(pressed);
                return true;
            }

            //<editor-fold defaultstate="collapsed" desc="Controle Tecla Liberada">
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.A:
                        System.out.println("Left Released");
                        moveLeft = false;
                        break;
                    case Input.Keys.D:
                        System.out.println("Right Released");
                        moveRight = false;
                        break;
                    case Input.Keys.W:
                        System.out.println("Up Released");
                        moveUp = false;
                        break;
                    case Input.Keys.S:
                        System.out.println("Down Released");
                        moveDown = false;
                        break;
                    default:
                }
                return true;
            }
        });//Adiciona comandos
        stage.addActor(new Atores.Jogador());
    }
}
