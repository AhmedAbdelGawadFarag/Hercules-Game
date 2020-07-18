package com.mygdx.game.gamestates;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dCollisionList;
import Box2dHelpers.Box2dConversions;
import Characters.GameCharacter;
import Characters.MainCharacter;
import Characters.StaticCharacters;
import INPUTS.UserINputs;
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

    private World world;

    private SpriteBatch batch;
    private Box2DDebugRenderer debug;
    private OrthographicCamera cam;
    private UserINputs input;


    private GameCharacter car;
    private GameCharacter BunchBag;

    private Level1Map lvl1;
    private Body Floor;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
		debug = new Box2DDebugRenderer(true, true, true, true, true, true);

		world = new World(new Vector2(0, -9.8f), true);

		input = new UserINputs();
		Gdx.input.setInputProcessor(input);

		car = new MainCharacter(world, new TextureAtlas("MainCharacter/Main.atlas"), 140, 700, 30, 60, 1.45f, input);
		BunchBag = new StaticCharacters(world, new TextureAtlas("BunchBag/Main.atlas"), 300, 400, 30, 60);


		cam = new OrthographicCamera(Box2dConversions.unitsToMetres(1280), Box2dConversions.unitsToMetres(960));
		cam.translate(640 / 200f, 480 / 200f);


		batch = new SpriteBatch();

		lvl1 = new Level1Map(cam, world);
		world.setContactListener(new Box2dCollideListeners());



	}


    public void update(float dt) {
        handleInput();


    }




	@Override
	public void draw(float dt) {

		world.step(dt, 3,3);

		lvl1.update();
		batch.setProjectionMatrix(cam.combined);

//        CameraManager.LockOnTarger(cam, new Vector2(car.body.getPosition()));
		cam.update();


		batch.begin();

		car.update(batch);
		car.CharacterState(dt);


		BunchBag.update(batch);


		batch.end();

		debug.render(world, cam.combined);


    }

    public void handleInput() {


    }

    public void dispose() {

    }
}
