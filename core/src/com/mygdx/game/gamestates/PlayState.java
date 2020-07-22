package com.mygdx.game.gamestates;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dConversions;
import Characters.*;
import Coins.GoldCoin;
import Coins.SilverCoin;
import GameObjects.GameObject;
import INPUTS.UserINputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    //private MainCharacter character;
    public static World world;

    public static SpriteBatch batch;
    public static Box2DDebugRenderer debug;
    public static OrthographicCamera cam;
    public static UserINputs input;


    public static GameObject Hercules;

    public static Level1Map lvl1;
    public static Body Floor;

    ArrayList<GameObject> enemies;
    ArrayList<GameObject> coins;
    ArrayList<GameObject> staticCaracters;


    HealthBar hl;

    MakeObjects makeObjects;
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        //character = new MainCharacter();
        debug = new Box2DDebugRenderer(true, true, true, true, true, true);

        world = new World(new Vector2(0, -9.8f), true);

        input = new UserINputs();
        Gdx.input.setInputProcessor(input);

        Hercules = new MainCharacter(world, new TextureAtlas("MainCharacter/Main.atlas"), 140, 700, 40, 80, 1f, input, "hercules", 10);

        makeObjects = new MakeObjects(world);

        cam = new OrthographicCamera(Box2dConversions.unitsToMetres(Gdx.graphics.getWidth()), Box2dConversions.unitsToMetres(Gdx.graphics.getHeight()));
//        cam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        batch = new SpriteBatch();

        lvl1 = new Level1Map(cam, world);
        world.setContactListener(new Box2dCollideListeners());

        //enmies
        enemies = new ArrayList<GameObject>();

        hl = new HealthBar(new TextureAtlas("HealthBar/Main.atlas"));


        coins = new ArrayList<GameObject>();


        staticCaracters = new ArrayList<GameObject>();

       makeObjects.MakeCoins(coins);
       makeObjects.MakeDragons(enemies);
       makeObjects.MakePillars(staticCaracters);
       makeObjects.MakeWoodenMonsters(enemies);
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


        updateCoins(batch, dt);

        //update enmeis array
        updateEnmies(batch, dt);

        updateStaticCharacters(batch, dt);

        if (Hercules.isIsdead() == false)//herecules is not dead
            hl.draw(batch, Hercules.getPosition(), Hercules);
        else {
            Save.gd.setTenativeScore(MainCharacter.money);
            gsm.setState(GameStateManager.GAMEOVER);
        }

        batch.end();

        Hercules.CharacterState(dt);

        //remove bodies


        debug.render(world, cam.combined);


    }

    private void cameraUpdate() {


        Vector2 position = Hercules.getPosition();

        position.x = (Hercules.getPosition().x);

        position.y = (Hercules.getPosition().y);


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


    private void updateCoins(SpriteBatch batch, float dt) {
//        System.out.print(enemies.size());
        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).update(batch);
            if (coins.get(i).isIsdead()) {
                coins.remove(i);
                continue;
            }

            coins.get(i).CharacterState(dt);
        }
    }

    private void updateStaticCharacters(SpriteBatch batch, float dt) {
//        System.out.print(enemies.size());
        for (int i = 0; i < staticCaracters.size(); i++) {
            staticCaracters.get(i).update(batch);
            if (staticCaracters.get(i).isIsdead()) {
                staticCaracters.remove(i);
                continue;
            }

            staticCaracters.get(i).CharacterState(dt);
        }
    }


    public void handleInput() {


    }

    public void dispose() {

    }
}
