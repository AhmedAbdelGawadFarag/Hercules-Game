package com.mygdx.game.gamestates;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dConversions;
import Characters.Dragons;
import Characters.GameCharacter;
import Characters.MainCharacter;
import Characters.StaticCharacters;
import INPUTS.UserINputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import map.Level1Map;

import java.util.ArrayList;
//import com.hercules.HerculesGame;


public class PlayState extends GameState {

    public static World world;

    public static SpriteBatch batch;
    public static Box2DDebugRenderer debug;
    public static OrthographicCamera cam;
    public static UserINputs input;


    public static GameCharacter Hercules;

    public static Level1Map lvl1;
    public static Body Floor;

    ArrayList<GameCharacter> enemies;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        debug = new Box2DDebugRenderer(true, true, true, true, true, true);

        world = new World(new Vector2(0, -9.8f), true);

        input = new UserINputs();
        Gdx.input.setInputProcessor(input);

        Hercules = new MainCharacter(world, new TextureAtlas("MainCharacter/Main.atlas"), 140, 700, 40, 80, 1f, input, "hercules", 1);


        cam = new OrthographicCamera(Box2dConversions.unitsToMetres(Gdx.graphics.getWidth()), Box2dConversions.unitsToMetres(Gdx.graphics.getHeight()));
        cam.translate(Box2dConversions.unitsToMetres(Gdx.graphics.getWidth() / 2), Box2dConversions.unitsToMetres(Gdx.graphics.getHeight() / 2));


        batch = new SpriteBatch();

        lvl1 = new Level1Map(cam, world);
        world.setContactListener(new Box2dCollideListeners());

        //enmies
        enemies = new ArrayList<GameCharacter>();
        enemies.add(new StaticCharacters(world, new TextureAtlas("BunchBag/Main.atlas"), 200, 400, 40, 80, "statchar", 2));
        enemies.add(new Dragons(world, new TextureAtlas("dragons/Main.atlas"), 500, 100, 40, 80, "statchar", 200, 2));


    }


    public void update(float dt) {
        handleInput();
    }


    @Override
    public void draw(float dt) {

        world.step(1 / 60f, 12, 2);
        lvl1.update();
        batch.setProjectionMatrix(cam.combined);

        cameraUpdate();


        batch.begin();

        Hercules.update(batch);

        //update enmeis array
        updateEnmies(batch, dt);


        batch.end();

        Hercules.CharacterState(dt);

        //remove bodies


        debug.render(world, cam.combined);


    }

    private void cameraUpdate() {
        Vector2 position = Hercules.getPosition();
        position.x = Box2dConversions.unitsToMetres(Hercules.getPosition().x);
        position.y = Box2dConversions.unitsToMetres(Hercules.getPosition().y);
        cam.position.set(position, 0);

        cam.update();
    }

    private void updateEnmies(SpriteBatch batch, float dt) {
//        System.out.print(enemies.size());
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(batch);
            if (enemies.get(i).isIsdead()) {
                enemies.remove(i);
                continue;
            }

            enemies.get(i).CharacterState(dt);
        }
    }


    public void handleInput() {


    }

    public void dispose() {

    }
}
