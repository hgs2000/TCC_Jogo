package br.edu.ifc.blumenau.woworlds.core.Patched;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameStart extends Game {

    private OrthographicCamera camera;


    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false);
        this.camera.update();
        Gdx.input.setInputProcessor(new InputProcessor());


        this.setScreen(new Level1(this, new Player(Classe.INICIADOR), camera, 1));
    }

    class InputProcessor extends InputAdapter {

        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.A:
                    
                    System.out.println("Ping");
                    break;
                case Input.Keys.D:
                    System.out.println("Pong");
                case Input.Keys.W:
                case Input.Keys.S:
                default:
            }
            return true;
        }
    }

}
