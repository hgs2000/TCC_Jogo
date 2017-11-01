package br.edu.ifc.blumenau.woworlds.core.Patched.Bases;

import br.edu.ifc.blumenau.woworlds.core.InputProcessor;
import br.edu.ifc.blumenau.woworlds.core.Patched.GameStart;
import br.edu.ifc.blumenau.woworlds.core.Patched.Level1;
import br.edu.ifc.blumenau.woworlds.core.Patched.Player;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static br.edu.ifc.blumenau.woworlds.core.Patched.Bases.Classe.INICIADOR;
import br.edu.ifc.blumenau.woworlds.core.TCC;

/**
 * Classe que extends Game
 *
 * @author tads
 */
public class MainGame extends TCC {

    //<editor-fold defaultstate="collapsed" desc="Variáveis">
    //Declaração de variáveis
    public OrthographicCamera camera;

    private boolean moveDown = false;
    private boolean moveUp = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    //Fim declaração de variáveis </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Iniciador">
    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false);
        this.camera.position.x = 74;
        this.camera.position.x = 248;
        this.camera.update();
        this.setScreen(new Level1(this, new Player(INICIADOR), camera, 1));
        Gdx.input.setInputProcessor(new InputProcessor(this));

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters de Movimentação">
    //Getters e setters de movimentação
    public boolean getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
    //Fim getters e setters da movimentação
    //</editor-fold>

}
