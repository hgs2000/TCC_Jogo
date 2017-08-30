package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Tools {

    public static TiledMap mapLoader(String path) {
        return (new TmxMapLoader().load(path));
    }
}
