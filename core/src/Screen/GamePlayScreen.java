package Screen;

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
import com.hercules.HerculesGame;

public class GamePlayScreen extends ScreenAdapter {

    private World world;
    private SpriteBatch batch;
    private Box2DDebugRenderer debug;
    private OrthographicCamera cam;
    private UserINputs input;


    private GameCharacter car;
    private GameCharacter BunchBag;
    
    private Level1Map lvl1;
    private Body Floor;

    public GamePlayScreen(HerculesGame game) {
        debug = new Box2DDebugRenderer(true, true, true, true, true, true);

        world = new World(new Vector2(0, -9.8f), true);

        input = new UserINputs();
        Gdx.input.setInputProcessor(input);

        car = new MainCharacter(world, new TextureAtlas("MainCharacter/Main.atlas"), 400, 400, 30, 60, 1.45f, input);
        BunchBag = new StaticCharacters(world, new TextureAtlas("BunchBag/Main.atlas"), 500, 400, 30, 60);


        cam = new OrthographicCamera(Box2dConversions.unitsToMetres(1280), Box2dConversions.unitsToMetres(960));
        cam.translate(640 / 200f, 480 / 200f);

        
        batch = new SpriteBatch();

       lvl1 = new Level1Map(cam, world);
        world.setContactListener(new Box2dCollideListeners());


       // MakeFloor();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 3,3);

        lvl1.update();
        batch.setProjectionMatrix(cam.combined);

//        CameraManager.LockOnTarger(cam, new Vector2(car.body.getPosition()));
        cam.update();


        batch.begin();

        car.update(batch);
        car.CharacterState(delta);


        BunchBag.update(batch);


        batch.end();

        debug.render(world, cam.combined);

    }

    public void MakeFloor() {

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;

        float zx = Box2dConversions.unitsToMetres(300);//convert x to meters
        float zy = Box2dConversions.unitsToMetres(200);// conver y to approprotaie meters

        def.position.set(zx, zy);

        Floor = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Box2dConversions.unitsToMetres(1000), Box2dConversions.unitsToMetres(20));


        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;


        Floor.createFixture(fdef);

        Box2dCollisionList.GiveCollisonBitToBody(Floor, Box2dCollisionList.BIT_FLOOR);

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
