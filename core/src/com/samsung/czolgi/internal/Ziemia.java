package com.samsung.czolgi.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 */

public class Ziemia implements Disposable {

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void draw(Camera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(0, 0, camera.viewportWidth, camera.viewportHeight,
            Color.FIREBRICK, Color.FIREBRICK, Color.SKY, Color.SKY);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 200,
            Color.BROWN, Color.BROWN, Color.GOLDENROD, Color.GOLDENROD);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
