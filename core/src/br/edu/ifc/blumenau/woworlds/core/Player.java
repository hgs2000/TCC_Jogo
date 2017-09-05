package br.edu.ifc.blumenau.woworlds.core;

public class Player {
    int life = 100;
    int lv;
    enum classe{
        mago, tank, ranger, rogue;
    }
   Item[] inventario;
   Equipamento arma, belt, ring, energyS;
}
