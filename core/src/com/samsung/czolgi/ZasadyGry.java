package com.samsung.czolgi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 *
 */

public class ZasadyGry extends DefaultStateMachine<GameScreen, ZasadyGry.Stan> {

    private static final String TAG = "ZasadyGry";

    public static final int MSG_STRZAL = 1;
    public static final int MSG_NASTEPNY_GRACZ = 2;

    Czolg gracz;
    Czolg czolgStrzelajacy;
    final List<Czolg> czolgi = new ArrayList<Czolg>();
    Iterator<Czolg> kolejka;

    public ZasadyGry(GameScreen graCzolgi) {
        setOwner(graCzolgi);
        setInitialState(Stan.TURA_GRACZA);
        MessageManager.getInstance().addListeners(this, MSG_STRZAL);
        MessageManager.getInstance().addListeners(this, MSG_NASTEPNY_GRACZ);
    }

    public void nowaGra() {
        gracz = new Czolg();
        gracz.setPosition(100, 100);

        Random random = new Random();
        for (int i = 0; i < 5; ++i) {
            Czolg czolg = new Czolg();
            czolg.setPosition((2+i) * 100, 100);
            czolg.flip(true, false);
            czolgi.add(czolg);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        gracz.draw(spriteBatch);
        for (Czolg przeciwnik : czolgi) {
            przeciwnik.draw(spriteBatch);
        }
    }

    public Czolg czyPociskTrafil(Pocisk pocisk) {
        for (Czolg c : czolgi) {
            if (c != czolgStrzelajacy && pocisk.czyTrafilWCzolg(c)) {
                return c;
            }
        }
        return null;
    }

    enum Stan implements State<GameScreen> {
        TURA_GRACZA {
            @Override
            public boolean onMessage(GameScreen entity, Telegram telegram) {
                if (telegram.message == MSG_STRZAL) {
                    entity.zasadyGry.czolgStrzelajacy = entity.zasadyGry.gracz;
                    entity.wystrzelPocisk((Vector2) telegram.extraInfo, entity.zasadyGry.gracz);
                }
                if (telegram.message == MSG_NASTEPNY_GRACZ) {
                    entity.zasadyGry.kolejka = entity.zasadyGry.czolgi.iterator();
                    entity.zasadyGry.changeState(TURA_PRZECIWNIKA);
                }
                return super.onMessage(entity, telegram);
            }
        },

        TURA_PRZECIWNIKA {
            @Override
            public void enter(GameScreen entity) {
                super.enter(entity);
                while (entity.zasadyGry.kolejka.hasNext()) {
                    entity.zasadyGry.czolgStrzelajacy = entity.zasadyGry.kolejka.next();
                    if (entity.zasadyGry.czolgStrzelajacy.czyZniszczony()) continue;
                    Vector2 cel = pomysl(entity.zasadyGry.czolgStrzelajacy);
                    entity.wystrzelPocisk(cel, entity.zasadyGry.czolgStrzelajacy);
                    return;
                }
                entity.zasadyGry.changeState(TURA_GRACZA);
            }

            private Vector2 pomysl(Czolg czolgStrzelajacy) {
                Random r = new Random();
                return new Vector2(r.nextFloat()-0.5f, r.nextFloat()).scl(r.nextInt(1000));
            }

            @Override
            public boolean onMessage(GameScreen entity, Telegram telegram) {
                if (telegram.message == MSG_NASTEPNY_GRACZ) {
                    entity.zasadyGry.changeState(TURA_PRZECIWNIKA);
                }
                return super.onMessage(entity, telegram);
            }
        },

        KONIEC_GRY {
            // todo
        };

        @Override
        public void enter(GameScreen entity) {
            Gdx.app.log(TAG, "enter " + name());
        }

        @Override
        public void update(GameScreen entity) {
            Gdx.app.log(TAG, "update " + name());
        }

        @Override
        public void exit(GameScreen entity) {
            Gdx.app.log(TAG, "exit " + name());
        }

        @Override
        public boolean onMessage(GameScreen entity, Telegram telegram) {
            return true;
        }
    }

}
