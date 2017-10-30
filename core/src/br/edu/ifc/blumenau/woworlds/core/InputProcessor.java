package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

    private TCC game;

    public InputProcessor(TCC jogo) {
        this.game = jogo;
    }

    //<editor-fold defaultstate="collapsed" desc="Controle Tecla Pressionada">
    @Override
    public boolean keyDown(int keycode) {
        String pressed = "";
        switch (keycode) {
            case Input.Keys.A:
                pressed = "Left Pressed;";
                game.setMoveLeft(true);
                break;
            case Input.Keys.D:
                pressed = "Right Pressed;";
                game.setMoveRight(true);
                break;
            case Input.Keys.W:
                pressed = "Up Pressed;";
                game.setMoveUp(true);
                break;
            case Input.Keys.S:
                pressed = "Down Pressed;";
                game.setMoveDown(true);
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Controle Tecla Liberada">
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                System.out.println("Left Released");
                game.setMoveLeft(false);
                break;
            case Input.Keys.D:
                System.out.println("Right Released");
                game.setMoveRight(false);
                break;
            case Input.Keys.W:
                System.out.println("Up Released");
                game.setMoveUp(false);
                break;
            case Input.Keys.S:
                System.out.println("Down Released");
                game.setMoveDown(false);
                break;
            default:
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Controle Mouse Apertado">
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return super.touchDown(screenX, screenY, pointer, button);
    }
//</editor-fold>

}
