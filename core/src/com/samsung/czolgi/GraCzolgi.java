package com.samsung.czolgi;

import com.badlogic.gdx.Game;
import com.samsung.czolgi.internal.Assets;
import com.samsung.czolgi.screens.MainMenuScreen;

public class GraCzolgi extends Game {

    public static final int EKRAN_SZEROKOSC = 800;
    public static final int EKRAN_WYSOKOSC = 480;

    private static GraCzolgi instance;

    public GraCzolgi() {
        instance = this;
    }

    public static GraCzolgi getInstance() {
        return instance;
    }

    @Override
    public void create() {
        Assets.initialize();
        setScreen(new MainMenuScreen());
    }
}
