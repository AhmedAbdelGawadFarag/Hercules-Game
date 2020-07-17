package Characters;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;

public abstract class GameCharacter {

    //world of the attributes
    public World world;
    public Body body;


    TextureAtlas atlas;
    TextureRegion currframe;

    Animation<TextureRegion> RunningAnimation;

    float RunningElapsedTime = 0;
    float speed;

    public GameCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height) {
        currframe = (atlas.findRegion("running"));
        this.atlas = atlas;
        this.world = world;

        //animation
        RunningAnimation = new Animation<TextureRegion>(1 / 30f, atlas.findRegions("running"));

        //making the character
        makeCharacter(x, y, width, height);

        Sprite temp = new Sprite(currframe);
        temp.setSize(width / 100f, height / 100f);

        //set the sprite
        body.setUserData(temp);
    }

    public void makeCharacter(float x, float y, int width, int height) {//dynamic character
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        x = Box2dConversions.unitsToMetres(x);//convert x to meters
        y = Box2dConversions.unitsToMetres(y);// conver y to approprotaie meters

        def.position.set(x, y);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Box2dConversions.unitsToMetres(width), Box2dConversions.unitsToMetres(height));


        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        body.createFixture(fdef);

    }


    public void SetFrame(Animation<TextureRegion> animation, boolean looping, float elapsedTime, boolean ReverseFrame) {

        currframe = animation.getKeyFrame(elapsedTime, looping);
        Sprite s = (Sprite) body.getUserData();
        s.setRegion(currframe);

        if (ReverseFrame == true)//reverse
            s.flip(true,false);



    }


    public void update(SpriteBatch batch) {
        //get currentframe

        Sprite sprite = (Sprite) body.getUserData();

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2f, body.getPosition().y - sprite.getHeight() / 2f);
        sprite.draw(batch);

    }

    public void ResetFrame() {


        SetFrame(RunningAnimation, false, RunningElapsedTime,false);

    }

    public abstract void CharacterState(float dt);

}