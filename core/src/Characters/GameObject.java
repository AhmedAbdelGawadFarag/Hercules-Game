package Characters;

import Box2dHelpers.Box2dConversions;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public abstract class GameObject {


    //world of the attributes
    protected World world;
    protected Body body;
    protected Fixture bodyfixture;
    protected TextureAtlas atlas;
    //frames
    protected TextureRegion currframe;

    protected boolean isdead = false;

    public boolean isIsdead() {
        return isdead;
    }

    float RunningElapsedTime = 0;
    float speed;

    public Fixture getBodyfixture() {
        return bodyfixture;
    }

    public void setBodyfixture(Fixture bodyfixture) {
        this.bodyfixture = bodyfixture;
    }

    public GameObject(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, int health) {
        currframe = atlas.findRegion("standing", 0);

        this.atlas = atlas;
        this.world = world;


        //animation
//        RunningAnimation = new Animation<TextureRegion>(1 / 15f, atlas.findRegions("running"));

        //making the character
        makeCharacter(x, y, width, height, FixtureName);


        Sprite temp = new Sprite(currframe);
        temp.setSize((width) / (Box2dConversions.ppm), (height) / (Box2dConversions.ppm));

        //set the sprite
        body.setUserData(new BodyData(temp, health));


    }


    public void makeCharacter(float x, float y, int width, int height, String FixtureName) {//dynamic character
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        float zx = Box2dConversions.unitsToMetres(x);//convert x to meters
        float zy = Box2dConversions.unitsToMetres(y);// conver y to approprotaie meters

        def.position.set(zx, zy);


        body = world.createBody(def);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Box2dConversions.unitsToMetres(width - 10) / 2, Box2dConversions.unitsToMetres(height) / 2);


        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        bodyfixture = body.createFixture(fdef);
        bodyfixture.setUserData(FixtureName);

    }


    public void SetFrameFromAnimation(Animation<TextureRegion> animation, boolean looping, float elapsedTime, boolean ReverseFrame) {
        if (currframe == null) return;//object is removed
        currframe = animation.getKeyFrame(elapsedTime, looping);
        setSprite(currframe, ReverseFrame);

    }

    public void setSprite(TextureRegion region, boolean reverse) {
        if (currframe == null) return;//object is removed

        Sprite s = this.getSpriteFromBody();

        s.setRegion(currframe);

        if (reverse == true)//reverse
            s.flip(true, false);


    }

    public void update(SpriteBatch batch) {
        //get currentframe

        if (currframe != null && isDead()) this.Remove();



        if (currframe == null) return; // object is removed

        Sprite sprite = this.getSpriteFromBody();

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2f, body.getPosition().y - sprite.getHeight() / 2f);
        sprite.draw(batch);


    }


    public void ResetFrame(boolean reverse) {

        currframe = atlas.findRegion("standing", 0);
        setSprite(currframe, reverse);
    }

    public void Remove() {
        if (currframe == null) return;//object is removed from the world
        isdead = true;
        world.destroyBody(body);
        currframe = null;
    }

    private Sprite getSpriteFromBody() {
        BodyData bd = (BodyData) body.getUserData();
        return bd.sprite;
    }

    public int getHealthFromBody() {

        BodyData bd = (BodyData) body.getUserData();

        return bd.health;
    }

    private boolean isDead() {
        System.out.println(currframe);
        return getHealthFromBody() == 0;

    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

    public abstract void CharacterState(float dt);


}