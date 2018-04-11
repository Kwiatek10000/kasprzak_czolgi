package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.samsung.czolgi.GraCzolgi;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = GraCzolgi.EKRAN_SZEROKOSC*2;
        config.height = GraCzolgi.EKRAN_WYSOKOSC*2;
		new LwjglApplication(new GraCzolgi(), config);
	}
}
