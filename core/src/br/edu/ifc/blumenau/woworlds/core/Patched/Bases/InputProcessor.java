package br.edu.ifc.blumenau.woworlds.core.Patched.Bases;

import br.edu.ifc.blumenau.woworlds.core.Patched.GameStart;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

    GameStart game;

    public InputProcessor(GameStart screen) {
        this.game = screen;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                System.out.println("Ping");
                game.setMoveLeft(true);
                break;
            case Input.Keys.D:
                System.out.println("Pong");
            case Input.Keys.W:
            case Input.Keys.S:
            default:
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                System.out.println("Ping");
                game.setMoveLeft(false);
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
