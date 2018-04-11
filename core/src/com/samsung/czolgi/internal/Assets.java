package com.samsung.czolgi.internal;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 *
 */

public class Assets {

    private static final AssetManager assetManager = new AssetManager();

    // zasoby
    private static final String TEX_CZOLG = "tank.png";
    private static final String TEX_WIEZYCZKA = "turret.png";
    private static final String TEX_POCISK = "pocisk.png";

    private static final String MUSIC_BG = "XX.mp3";

    private static final String FONT_DEFAULT = "fonts/Roboto-Bold.ttf";


    public static void initialize() {
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(BitmapFont.class, new FreetypeFontLoader(new InternalFileHandleResolver()));

        assetManager.load(TEX_CZOLG, Texture.class);
        assetManager.load(TEX_WIEZYCZKA, Texture.class);
        assetManager.load(TEX_POCISK, Texture.class);
        assetManager.load(MUSIC_BG, Music.class);

        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = FONT_DEFAULT;
        params.fontParameters.size = 128;
        params.fontParameters.shadowOffsetX = 4;
        params.fontParameters.shadowOffsetY = 4;
        params.fontParameters.minFilter = Texture.TextureFilter.Linear;
        params.fontParameters.magFilter = Texture.TextureFilter.Linear;
        assetManager.load(FONT_DEFAULT, BitmapFont.class, params);

        assetManager.finishLoading();
    }

    public static void dispose() {
        assetManager.dispose();
    }

    public static Texture getCzolgTex() {
        return assetManager.get(TEX_CZOLG);
    }

    public static Texture getWiezyczkaTex() {
        return assetManager.get(TEX_WIEZYCZKA);
    }

    public static Texture getPociskTex() {
        return assetManager.get(TEX_POCISK);
    }

    public static Music getMusicBg() {
        return assetManager.get(MUSIC_BG);
    }

    public static BitmapFont getFont() {
        return assetManager.get(FONT_DEFAULT);
    }
}
