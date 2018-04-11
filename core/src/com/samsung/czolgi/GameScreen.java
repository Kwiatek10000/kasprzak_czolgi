package com.samsung.czolgi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.samsung.czolgi.fizyka.Symulator;
import com.samsung.czolgi.internal.Assets;
import com.samsung.czolgi.internal.Celowanie;
import com.samsung.czolgi.internal.Trajektoria;
import com.samsung.czolgi.internal.Ziemia;
import com.samsung.czolgi.screens.AbstractScreen;

/**
 *
 */
public class GameScreen extends AbstractScreen {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private BitmapFontCache wygranaTekst;
    private BitmapFontCache przegranaTekst;

    final Symulator symulator = new Symulator();

    private Celowanie celowanie;
    private Trajektoria trajektoria;

    Pocisk pocisk;

    private Ziemia ziemia;

    private boolean wystrzelonyPocisk;



    final ZasadyGry zasadyGry = new ZasadyGry(this);


    public GameScreen() {
        zasadyGry.nowaGra();

        batch = new SpriteBatch();

        Music music = Assets.getMusicBg();
        music.setLooping(true);
        music.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GraCzolgi.EKRAN_SZEROKOSC, GraCzolgi.EKRAN_WYSOKOSC);

        ziemia = new Ziemia();
        przygotujTeksty();

        pocisk = new Pocisk();
        symulator.dodaj(pocisk.cialo);

        celowanie = new Celowanie();
        trajektoria = new Trajektoria(celowanie, zasadyGry.gracz);
    }

    private void przygotujTeksty() {
        BitmapFont font = Assets.getFont();
        wygranaTekst = font.newFontCache();
        wygranaTekst.setColor(new Color(0, 0, 0, 1));
        wygranaTekst.setText("YOU WIN", Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        przegranaTekst = font.newFontCache();
        przegranaTekst.setColor(Color.RED);
        przegranaTekst.setText("YOU LOSE", Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);

    }

    @Override
    public void render(float delta) {
        symulator.symuluj();
        camera.update();

        ziemia.draw(camera);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        zasadyGry.draw(batch);
        if (wystrzelonyPocisk) {
            pocisk.draw(batch);
        }
        batch.end();

        celowanie.draw(camera);
        trajektoria.draw(camera);

        sprawdzStanPocisku();
    }

    private void sprawdzStanPocisku() {
        if (wystrzelonyPocisk) {
            Czolg c = null;
            if ((c = zasadyGry.czyPociskTrafil(pocisk)) != null) {
                wystrzelonyPocisk = false;
                c.zniszcz();
                MessageManager.getInstance().dispatchMessage(ZasadyGry.MSG_NASTEPNY_GRACZ);
            }
            if (pocisk.czyPozaEkranem()) {
                wystrzelonyPocisk = false;
                MessageManager.getInstance().dispatchMessage(ZasadyGry.MSG_NASTEPNY_GRACZ);
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        Assets.dispose();
    }

    void wystrzelPocisk(Vector2 namiar, Czolg czolgStrzelajacy) {
        pocisk.cialo.setPozycja(czolgStrzelajacy.getPosition());
        pocisk.cialo.setPredkosc(namiar);
        czolgStrzelajacy.obrocWiezyczke(namiar.nor().angle());
        wystrzelonyPocisk = true;
    }
}
