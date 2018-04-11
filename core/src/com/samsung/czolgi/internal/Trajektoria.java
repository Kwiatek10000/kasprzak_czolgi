package com.samsung.czolgi.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.samsung.czolgi.Czolg;
import com.samsung.czolgi.fizyka.Cialo;
import com.samsung.czolgi.fizyka.Symulator;

/**
 *
 */

public class Trajektoria {

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Symulator symulator = new Symulator();

    private final Celowanie celowanie;
    private final Czolg czolg;

    public Trajektoria(Celowanie celowanie, Czolg czolg) {
        this.celowanie = celowanie;
        this.czolg = czolg;
    }

    public void draw(Camera camera) {
        if (celowanie.isTouching()) {
            Vector2 positon = czolg.getPosition();
            Cialo cialo = new Cialo();
            cialo.setPozycja(positon);
            cialo.setPredkosc(celowanie.getCel());
            symulator.dodaj(cialo);

            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            Vector2 poprzedniaPozycja = positon.cpy();
            Color color = new Color(Color.WHITE); // white
            for (int i = 0; i < 20; ++i) {
                symulator.symuluj(0.5f);
                shapeRenderer.setColor(color);
                shapeRenderer.line(poprzedniaPozycja.x, poprzedniaPozycja.y, cialo.getX(), cialo.getY());
                poprzedniaPozycja.set(cialo.getX(), cialo.getY());
                color.a -= 1.0f/20.0f;
            }
            shapeRenderer.end();
            symulator.usun(cialo);
            Gdx.gl.glEnable(GL20.GL_BLEND);

            czolg.obrocWiezyczke(celowanie.getCel().angle());
        }
    }
}
