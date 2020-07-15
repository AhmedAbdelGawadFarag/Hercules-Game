package com.hercules;

import Characters.MainCharacter;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import  Characters.GameCharacter;
import  Characters.MainCharacter;
public class Hercules extends ApplicationAdapter {
    SpriteBatch batch;

    int currframe = 1;
    GameCharacter m;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(1024,400);
        batch = new SpriteBatch();
        m = new MainCharacter(new TextureAtlas(Gdx.files.internal("MainCharacter.atlas")));

    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        m.Move();
        m.attack();

        m.draw(batch);


        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }


}
