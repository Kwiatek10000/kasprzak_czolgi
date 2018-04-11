package com.samsung.czolgi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.samsung.czolgi.internal.Assets;


public class Czolg extends Sprite {

    private final Sprite wiezyczka;

    boolean zniszczony = false;

    public Czolg() {
        super(Assets.getCzolgTex());

        wiezyczka = new Sprite(Assets.getWiezyczkaTex());
        wiezyczka.setOrigin(16, 18);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        wiezyczka.setPosition(x, y);
    }

    public void draw(Batch batch) {
        super.draw(batch);
        wiezyczka.draw(batch);
    }

    public void obrocWiezyczke(float stopnie) {
        wiezyczka.setRotation(stopnie);
    }

    public Vector2 getPosition() {
        return new Vector2(getX(), getY()).add(getWidth()/2, getHeight()/2);
    }

    public void zniszcz() {
        zniszczony = true;
        setColor(Color.BLACK);
        wiezyczka.setColor(Color.BLACK);
    }

    public boolean czyZniszczony() {
        return zniszczony;
    }
}
