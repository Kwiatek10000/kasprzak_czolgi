package com.samsung.czolgi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samsung.czolgi.GameScreen;
import com.samsung.czolgi.GraCzolgi;

public class MainMenuScreen extends AbstractScreen {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    public MainMenuScreen() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // tell the camera to update its matrices.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                GraCzolgi.getInstance().setScreen(new GameScreen());
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Czolgi", 10, Gdx.graphics.getHeight()/2);
        font.draw(batch, "Touch screen to start", 10, Gdx.graphics.getHeight()/2-50);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
