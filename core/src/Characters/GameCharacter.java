package Characters;

import Box2dHelpers.Box2dConversions;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;

public abstract class GameCharacter {

    //world of the attributes
    public World world;
    public Body body;

    TextureAtlas atlas;
    //frames
    TextureRegion currframe;


    float RunningElapsedTime = 0;
    float speed;

    public GameCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height) {
        currframe = atlas.findRegion("standing", 0);

        this.atlas = atlas;
        this.world = world;

        //animation
//        RunningAnimation = new Animation<TextureRegion>(1 / 15f, atlas.findRegions("running"));

        //making the character
        makeCharacter(x, y, width, height);


        Sprite temp = new Sprite(currframe);
        temp.setSize((width) / (Box2dConversions.ppm / 2f), (height) / (Box2dConversions.ppm / 2f));

        //set the sprite
        body.setUserData(temp);


    }


    public void makeCharacter(float x, float y, int width, int height) {//dynamic character
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        float zx = Box2dConversions.unitsToMetres(x);//convert x to meters
        float zy = Box2dConversions.unitsToMetres(y);// conver y to approprotaie meters

        def.position.set(zx, zy);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Box2dConversions.unitsToMetres(width-10), Box2dConversions.unitsToMetres(height));


        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        body.createFixture(fdef);


    }



    public void SetFrameFromAnimation(Animation<TextureRegion> animation, boolean looping, float elapsedTime, boolean ReverseFrame) {

        currframe = animation.getKeyFrame(elapsedTime, looping);
        setSprite(currframe, ReverseFrame);


    }

    public void setSprite(TextureRegion region, boolean reverse) {
        Sprite s = (Sprite) body.getUserData();
        s.setRegion(currframe);

        if (reverse == true)//reverse
            s.flip(true, false);


    }

    public void update(SpriteBatch batch) {
        //get currentframe

        if (currframe == null) return; // object is removed

        Sprite sprite = (Sprite) body.getUserData();

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2f, body.getPosition().y - sprite.getHeight() / 2f);
        sprite.draw(batch);


    }


    public void ResetFrame(boolean reverse) {

        currframe = atlas.findRegion("standing", 0);
        setSprite(currframe, reverse);
    }

    public void Remove() {
        if (currframe == null) return;//object is removed from the world

        world.destroyBody(body);
        currframe = null;
    }

    public abstract void CharacterState(float dt);

}