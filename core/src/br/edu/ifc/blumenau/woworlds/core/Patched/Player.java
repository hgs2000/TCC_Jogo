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
        TextureRegion[][] load = TextureRegion.split(texture, texture.getWidth() / 4, texture.getHeight() / 4);
        TextureRegion[] frames = new TextureRegion[load.length * load[0].length];
        int k = 0;
        for (TextureRegion[] aLoad : load) {
            for (TextureRegion anALoad : aLoad) {
                frames[k++] = anALoad;
            }
        }

        //Bloco animação para baixo
        TextureRegion anim[] = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            anim[i] = frames[i];
        }
        walkingdown = new Animation<TextureRegion>(0.2f, anim);

        anim = new TextureRegion[1];
        anim[0] = frames[0];
        stopdown = new Animation<TextureRegion>(0.2f, anim);

        //Bloco animação para cima
        anim = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            anim[i] = frames[i + 4];
        }
        walkingup = new Animation<TextureRegion>(0.2f, anim);

        anim = new TextureRegion[1];
        anim[0] = frames[4];
        stopup = new Animation<TextureRegion>(0.2f, anim);

        //Bloco animação para esquerda
        anim = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            anim[i] = frames[i + 8];
        }
        walkingleft = new Animation<TextureRegion>(0.2f, anim);

        anim = new TextureRegion[1];
        anim[0] = frames[8];
        stopleft = new Animation<TextureRegion>(0.2f, anim);

        //Bloco animação para direita
        anim = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            anim[i] = frames[i + 12];
        }
        walkingright = new Animation<TextureRegion>(0.2f, anim);

        anim = new TextureRegion[1];
        anim[0] = frames[12];
        stopright = new Animation<TextureRegion>(0.2f, anim);

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

    /**
     * Muda o estado atual do jogador
     *
     * @param current STOP_DOWN, STOP_UP, STOP_LEFT, STOP_RIGHT, <br>
     * WALK_DOWN, WALK_UP, WALK_LEFT, WALK_RIGHT
     */
    public void changeState(EstadoPlayer current) {
        this.state = current;
    }

    Animation<TextureRegion> getCurrentAnimation() {
        switch (state) {
            case WALK_DOWN:
                return walkingdown;
            case STOP_DOWN:
                return stopdown;
            case WALK_UP:
                return walkingup;
            case STOP_UP:
                return stopup;
            case WALK_LEFT:
                return walkingleft;
            case STOP_LEFT:
                return stopleft;
            case WALK_RIGHT:
                return walkingright;
            case STOP_RIGHT:
                return stopright;
            default:
                return stopdown;
        }
    }

}

enum EstadoPlayer {
    STOP_DOWN, STOP_UP, STOP_LEFT, STOP_RIGHT,
    WALK_DOWN, WALK_UP, WALK_LEFT, WALK_RIGHT
}
