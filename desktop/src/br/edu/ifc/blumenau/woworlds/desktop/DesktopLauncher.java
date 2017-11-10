package br.edu.ifc.blumenau.woworlds.desktop;

import br.edu.ifc.blumenau.woworlds.core.TCC;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "World Of Worlds";
        config.width = 1280;
        config.height = 720;
        config.fullscreen = false;
        config.resizable = false;
        config.useHDPI = true;
        //config.foregroundFPS = 30;
        config.addIcon("icon.png", Files.FileType.Internal);
        new LwjglApplication(new TCC(), config);
    }
}
