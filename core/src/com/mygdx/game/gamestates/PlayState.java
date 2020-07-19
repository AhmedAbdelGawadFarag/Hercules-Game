package com.mygdx.game.gamestates;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dCollisionList;
import Box2dHelpers.Box2dConversions;
import Characters.GameCharacter;
import Characters.MainCharacter;
import Characters.StaticCharacters;
import INPUTS.UserINputs;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import map.Level1Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
//import com.hercules.HerculesGame;


public class PlayState extends GameState {

	public static World world;

    public static SpriteBatch batch;
    public static Box2DDebugRenderer debug;
	public static OrthographicCamera cam;
	public static UserINputs input;


	public static GameCharacter car;
	public static GameCharacter BunchBag;

	public static Level1Map lvl1;
	public static Body Floor;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
		debug = new Box2DDebugRenderer(true, true, true, true, true, true);

		world = new World(new Vector2(0, -9.8f), true);

		input = new UserINputs();
		Gdx.input.setInputProcessor(input);

		car = new MainCharacter(world, new TextureAtlas("MainCharacter/Main.atlas"), 140, 700, 20, 80, 1f, input);
		BunchBag = new StaticCharacters(world, new TextureAtlas("BunchBag/Main.atlas"), 200, 400, 20, 80);


		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/1);


		batch = new SpriteBatch();

		lvl1 = new Level1Map(cam, world);
		world.setContactListener(new Box2dCollideListeners());



	}


    public void update(float dt) {
        handleInput();
    }




	@Override
	public void draw(float dt) {

		lvl1.update();
		batch.setProjectionMatrix(cam.combined);

		cam.update();


		batch.begin();
		world.step(1/60f, 12,2);

		car.update(batch);
		car.CharacterState(dt);



		BunchBag.update(batch);


		batch.end();
		cameraUpdate(dt);
		debug.render(world, cam.combined.scl(200f));


    }

	public void cameraUpdate(float delta) {
		Vector3 position = cam.position;
		position.x = Box2dConversions.metresToUnits(car.body.getPosition().x);
		position.y = Box2dConversions.metresToUnits(car.body.getPosition().y);
		cam.position.set(position);

		cam.update();
	}


    public void handleInput() {


    }

    public void dispose() {

    }
}
