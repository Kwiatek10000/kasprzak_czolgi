package com.samsung.czolgi.fizyka;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;


/**
 * Prosty symulator fizyki.
 */
public class Symulator {

    static final float GRAWITACJA = 9.81f; // [m/s2]

    final List<Cialo> ciala = new ArrayList<Cialo>();

    public void dodaj(Cialo cialo) {
        ciala.add(cialo);
    }

    public void usun(Cialo cialo) {
        ciala.remove(cialo);
    }

    /**
     * Wywoluj funkcje symulacji fizyki w dla kazdej klatki obrazu.
     *
     * Ta funkcja symuluje fizyke w czasie rzeczywistym.
     */
    public void symuluj() {
        symuluj(Gdx.graphics.getDeltaTime());
    }

    /**
     * Tak jak wyzej, lecz pozwala na manipulacje czasem. ;-)
     *
     * @param deltaT delta time
     */
    public void symuluj(float deltaT) {
        for (int i = 0; i < ciala.size(); ++i) {
            Cialo cialo = ciala.get(i);

            // TODO napisz poprawną symulację fizyki ciał stałych
            cialo.x++;
            cialo.y++;
        }
    }

}
