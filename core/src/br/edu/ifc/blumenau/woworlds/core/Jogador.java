package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Jogador {

    //Controle status jogador
    private int vida_maxima;
    private int vida;
    private int dano = 5;
    private EstadoJogador estado_atual;
    //End status

    //<editor-fold> Propriedades visuais
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

    private Item[] inventario;
    private Equipamento arma, cinto, anel, energyS;
    private int nivel;
    int experience_points = 0;

    private ClasseJogador classe;

    //Getters e setters
    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperience_points() {
        return experience_points;
    }

    public void setExperience_points(int experience_points) {
        this.experience_points = experience_points;
    }


    public void addXP(int xp) {
        this.experience_points += xp;
    }

    public void getCurrentLevel() {
        if (experience_points >= (nivel * 10)) {
            experience_points = 0;
            nivel += 1;
            dano = 5 * nivel;
        }
    }

    public void setClasse(ClasseJogador classe) {
        this.classe = classe;
        switch (classe) {
            case MAGO:

                break;
            case CAVALEIRO:

                break;
            case ASSASSINO:

                break;
            default:
                this.vida_maxima = 20;
                this.vida = this.vida_maxima;
                this.nivel = 1;
                this.dano = 5;
        }
    }

    public void setEstadoAtual(EstadoJogador current) {
        this.estado_atual = current;
    }

    public EstadoJogador getEstadoAtual() {
        return estado_atual;
    }

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

    public Animation<TextureRegion> getCurrentAnimation() {
        switch (estado_atual) {
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

    public String getClasse() {
        return classe.toString();
    }

    public Jogador() {
        try {
            this.setClasse(ClasseJogador.INICIADOR);
            switch (classe) {
                case ASSASSINO:
                case INICIADOR:
                case CAVALEIRO:
                case MAGO:
                    this.texture = new Texture(Gdx.files.internal("Knight_Walk.png"));
            }
            setAnimation();
            this.setEstadoAtual(EstadoJogador.STOP_DOWN);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    enum ClasseJogador {
        INICIADOR, MAGO, CAVALEIRO, ASSASSINO
    }

    enum EstadoJogador {
        STOP_DOWN, STOP_UP, STOP_LEFT, STOP_RIGHT,
        WALK_DOWN, WALK_UP, WALK_LEFT, WALK_RIGHT
    }

}

