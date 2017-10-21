package br.edu.ifc.blumenau.woworlds.core;

public class Player {

    int life = 100;
    int lv = 1;
    int xp = 0;
    int dano = 5;

    enum classe {
        mago, tank, ranger, rogue;
    }

    Item[] inventario;
    Equipamento arma, belt, ring, energyS;

    public void addXp(int xp) {
        this.xp += xp;
    }

    public void lvCheck() {
        if (xp >= (lv * 10)) {
            xp = 0;
            lv += 1;
            dano = 5 * lv;
        }
    }

}
