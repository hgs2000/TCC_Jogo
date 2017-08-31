package br.edu.ifc.blumenau.woworlds.core.testes;

import com.badlogic.gdx.Game;

public class WorldOfWorlds extends Game {

    public final int MENU = 0;
    public final int OPCOES = 1;
    public final int JOGO = 2;
    public final int PAUSE = 3;
    public final int FIM = 4;
    public final int CARREGANDO = 5;

    private MenuScreen menu;
    private LoadingScreen carregando;
    private JogoScreen jogo;
    private PausaScreen pausa;
    private FimScreen fim;
    private OpcoesScreen opcoes;

    @Override
    public void create() {

    }

    public void trocaTela(int tela) {
        switch (tela) {
            case MENU:
                if (menu == null) {
                    menu = new MenuScreen(this);
                    this.setScreen(menu);
                }
                break;
            case OPCOES:
                if (carregando == null) {
                    opcoes = new OpcoesScreen(this);
                    this.setScreen(opcoes);
                }
                break;
            case JOGO:
                if (jogo == null) {
                    jogo = new JogoScreen(this);
                    this.setScreen(jogo);
                }
                break;
            case PAUSE:
                if (pausa == null) {
                    pausa = new PausaScreen(this);
                    this.setScreen(pausa);
                }
                break;
            case FIM:
                if (fim == null) {
                    fim = new FimScreen(this);
                    this.setScreen(fim);
                }
                break;
        }
    }
}
