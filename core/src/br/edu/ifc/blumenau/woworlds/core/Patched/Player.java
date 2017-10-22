package br.edu.ifc.blumenau.woworlds.core.Patched;

import br.edu.ifc.blumenau.woworlds.core.Patched.Bases.Classe;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Classe utilizada pelo jogo para representar o jogador
 */
public class Player {

    //Declaração de variáveis
    //Variáveis gerais
    private Classe classe = Classe.INICIADOR;

    private EstadoPlayer state = EstadoPlayer.STOP_DOWN;
    private int hp = 100;
    private int dano = 0;

    private int curXp = 0;
    private int curLevel = 1;

    //Variáveis de textura
    private Texture texture;
    private Animation<TextureRegion> walkingup;
    private Animation<TextureRegion> stopup;
    private Animation<TextureRegion> walkingdown;
    private Animation<TextureRegion> stopdown;
    private Animation<TextureRegion> walkingleft;
    private Animation<TextureRegion> stopleft;
    private Animation<TextureRegion> walkingright;
    private Animation<TextureRegion> stopright;
    //Fim declaração de variáveis

    //Construtores da classe

    /**
     * @param classe pode ser MAGO, CAVALEIRO ou ASSASSINO
     */
    public Player(Classe classe) {

        try {
            this.classe = classe;
            switch (classe) {
                case ASSASSINO:
                case INICIADOR:
                case CAVALEIRO:
                case MAGO:
                    this.texture = new Texture(Gdx.files.internal("Knight_Walk.png"));
            }
            setAnimation();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Métodos privados
    private void setAnimation() throws Exception {
        new TextureRegion();
        TextureRegion[][] load = TextureRegion.split(texture, texture.getWidth() / 4, texture.getHeight() / 4);
        TextureRegion[] frames = new TextureRegion[4 * 4];

        System.arraycopy(load[0], 0, frames, 0, 4);
        walkingdown = new Animation<TextureRegion>(0.2f, frames);

        //System.arraycopy(load[0], 0, frames, 4, 4);
        for (int col = 0; col < 4; col++) {
            frames[col] = load[1][col];
        }
        walkingup = new Animation<TextureRegion>(0.2f, frames);

        System.arraycopy(load[0], 0, frames, 8, 4);
        walkingleft = new Animation<TextureRegion>(0.2f, frames);

        System.arraycopy(load[0], 0, frames, 12, 4);
        walkingright = new Animation<TextureRegion>(0.2f, frames);
    }

    //Métodos públicos
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public EstadoPlayer getState() {
        return state;
    }

    public void setState(EstadoPlayer state) {
        this.state = state;
    }

    public int getCurXp() {
        return curXp;
    }

    public void setCurXp(int curXp) {
        this.curXp = curXp;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public void changeState(EstadoPlayer current) {
        this.state = current;
    }

    Animation<TextureRegion> getCurrentAnimation() {
        //switch (state) {
        //          case WALK_DOWN:
        return walkingdown;
        //    }
    }

}

enum EstadoPlayer {
    STOP_DOWN, STOP_UP, STOP_LEFT, STOP_RIGHT,
    WALK_DOWN, WALK_UP, WALK_LEFT, WALK_RIGHT
}

