package br.edu.ifc.blumenau.woworlds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.edu.ifc.blumenau.woworlds.core.TCC;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.useGL30 = true;
                config.title = "World Of World";
                config.width = 1280;
                config.height = 720;
                config.fullscreen = false;
		new LwjglApplication(new TCC(), config);
	}
}
