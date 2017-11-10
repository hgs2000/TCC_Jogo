package scene2d;

import com.badlogic.gdx.Screen;
import java.util.ArrayList;

public class TrocaTela implements ITrocaTela {

    ArrayList<Screen> telas;
    private boolean telaAtualAcabou = false;
    private int indiceAtual;

    TrocaTela(Screen tela) {
        telas = new ArrayList<Screen>();
    }

    public void addTela(Screen tela) {
        telas.add(tela);
    }

    @Override
    public void fimTelaAtual() {
        telaAtualAcabou = true;
    }

    @Override
    public Screen getProximaTela() {
        if (telaAtualAcabou) {
            telaAtualAcabou = false;
            indiceAtual++;
        }

        if (telas.size() > indiceAtual) {
            return telas.get(indiceAtual);
        } else {
            return telas.get(0);
        }
    }

}
