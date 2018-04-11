package com.samsung.czolgi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.samsung.czolgi.fizyka.Cialo;
import com.samsung.czolgi.internal.Assets;


public class Pocisk extends Sprite {

    final Cialo cialo = new Cialo();

    public Pocisk() {
        super(Assets.getPociskTex());
        setScale(0.5f);
    }

    @Override
    public void draw(Batch batch) {
        setPosition(cialo.getX(), cialo.getY());
        super.draw(batch);
    }

    public boolean czyTrafilWCzolg(Czolg czolg) {
        return czolg.getBoundingRectangle().overlaps(getBoundingRectangle());
    }

    public boolean czyPozaEkranem() {
        return getX() > Gdx.graphics.getWidth() || getX() < 0 ||
                getY() > Gdx.graphics.getHeight() || getY() < 0;
    }
}
