package br.edu.ifc.blumenau.woworlds.desktop;

import br.edu.ifc.blumenau.woworlds.core.Patched.GameStart;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopGameTest {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "World Of Worlds";
        config.width = 1280;
        config.height = 720;
        config.fullscreen = false;
        config.resizable = false;
        config.useHDPI = true;
        new LwjglApplication(new GameStart(), config);
    }

}
