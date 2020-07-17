package Screen;

import Characters.Box2dConversions;
import Characters.GameCharacter;
import Characters.MainCharacter;
import Characters.UserINputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.hercules.HerculesGame;

public class GamePlayScreen extends ScreenAdapter {

    private World world;
    private SpriteBatch batch;
    private GameCharacter car;
    private Box2DDebugRenderer debug;
    private OrthographicCamera cam;
    private UserINputs input;

    public GamePlayScreen(HerculesGame game) {
        debug = new Box2DDebugRenderer(true, true, true, true, true, true);

        world = new World(new Vector2(0, 0), true);

        input = new UserINputs();
        Gdx.input.setInputProcessor(input);

        car = new MainCharacter(world, new TextureAtlas("main.atlas"), 20, 20, 39, 75, 1.45f, input);


        cam = new OrthographicCamera(Box2dConversions.unitsToMetres(1280), Box2dConversions.unitsToMetres(960));

        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 3, 3);


        batch.setProjectionMatrix(cam.combined);

        CameraManager.LockOnTarger(cam, new Vector2(car.body.getPosition()));
//        cam.update();


        batch.begin();

        car.update(batch);
        car.CharacterState(delta);


        batch.end();

        debug.render(world, cam.combined);

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
