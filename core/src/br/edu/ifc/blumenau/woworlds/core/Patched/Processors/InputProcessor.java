package br.edu.ifc.blumenau.woworlds.core.Patched.Processors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

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
