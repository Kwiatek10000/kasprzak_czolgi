package com.samsung.czolgi.fizyka;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */

public class Cialo {

    // [m]
    float x;
    float y;

    // [m/s]
    float predkoscX;
    float predkoscY;

    // [m/s2]
    float przyspX;
    float przyspY;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPredkosc(Vector2 predkosc) {
        this.predkoscX = predkosc.x;
        this.predkoscY = predkosc.y;
    }

    public void setPozycja(Vector2 pozycja) {
        this.x = pozycja.x;
        this.y = pozycja.y;
    }

}
