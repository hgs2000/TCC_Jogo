package br.edu.ifc.blumenau.woworlds.core.Patched;

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


    private int curXp = 0;
    private int curLevel;

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
    Player(Classe classe) {

        try {
            this.classe = classe;
            switch (classe) {
                case ASSASSINO:
                case INICIADOR:
                case CAVALEIRO:
                case MAGO:
                    this.texture = new Texture(Gdx.files.internal("Knight_Walk.png"));
            }
            this.walkingdown = setAnimation(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Métodos privados

    /**
     * @param way 1 para baixo, 2 para cima, 3 para a esquerda, 4 para a direita
     * @return animação direcional
     */
    private Animation<TextureRegion> setAnimation(int way) throws Exception {

        float elapsedTime = 0;

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.33f);

        switch (way) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
            default:
                throw new Exception("Opção " + way + " inválida");
        }
        return null;
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

    public void changeState(EstadoPlayer current) {
        this.state = current;
    }


}

enum EstadoPlayer {
    STOP_DOWN, STOP_UP, STOP_LEFT, STOP_RIGHT,
    WALK_DOWN, WALK_UP, WALK_LEFT, WALK_RIGHT
}

enum Classe {
    INICIADOR, MAGO, CAVALEIRO, ASSASSINO
}
