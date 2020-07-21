package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.gamestates.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.xml.stream.events.Characters;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MyGdxGame extends ApplicationAdapter {
	
	public static int WIDTH;
	public static int HEIGHT;
	public static OrthographicCamera cam;
	
	private GameStateManager gsm;

	//Splash Screen
	TextureRegion textureRegion;
	Texture splashscreen;
	Stage stage;
	boolean splash = false;
	
	@Override
	public void create () {
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();
		
		
		gsm = new GameStateManager();

		//Splash Screen
		stage = new Stage();
		splashscreen = new Texture(Gdx.files.internal("splash.jpg"));
		splashscreen.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		textureRegion = new TextureRegion(splashscreen);
		Image actor = new Image(textureRegion);
		actor.getColor().a=0;
		actor.setScale(660, 660);
		actor.setSize(1280,960);
		actor.setPosition(Gdx.graphics.getWidth()/2-actor.getWidth()/2,
				Gdx.graphics.getHeight()/2-actor.getHeight()/2);
		actor.setScale(1);

		SequenceAction actions = new SequenceAction(sequence(fadeIn(1f), delay(2.5f), fadeOut(2.5f),
				run(new Runnable() {
					@Override
					public void run() {
						splash=true;
					}
				})));

		actor.addAction(actions);
		stage.addActor(actor);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Splash Screen
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		if(splash) {
			gsm.update(Gdx.graphics.getDeltaTime());
			gsm.draw(Gdx.graphics.getDeltaTime());
		}
	}
	
	@Override
	public void dispose () {
		
	}
}