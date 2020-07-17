package com.hercules;

import Characters.MainCharacter;
import Screen.GamePlayScreen;
import Screen.MenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import  Characters.GameCharacter;
import  Characters.MainCharacter;

public class HerculesGame extends Game {
    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(1280,960);

        setScreen(new GamePlayScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
