package Screen;

import Characters.Box2dConversions;
import Characters.GameCharacter;
import Characters.MainCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.hercules.HerculesGame;

public class GamePlayScreen extends ScreenAdapter {

    World world;
    SpriteBatch batch;
    GameCharacter car;
    Box2DDebugRenderer debug;
    OrthographicCamera cam;

    public GamePlayScreen(HerculesGame game) {
        debug = new Box2DDebugRenderer(true, true, true, true, true, true);

        world = new World(new Vector2(0, 0), true);

        car = new MainCharacter(world, new TextureAtlas("main.atlas"), 20, 20,39,75);


        cam = new OrthographicCamera(Box2dConversions.unitsToMetres(600), Box2dConversions.unitsToMetres(500));
        batch = new SpriteBatch();
//        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 3, 3);
        cam.update();



        batch.setProjectionMatrix(cam.combined);


        batch.begin();


        car.update(batch);


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
