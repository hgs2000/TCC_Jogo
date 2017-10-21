package br.edu.ifc.blumenau.woworlds.core;

public class Player {

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    int life = 100;
    int lv = 1;
    int xp = 0;

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

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
