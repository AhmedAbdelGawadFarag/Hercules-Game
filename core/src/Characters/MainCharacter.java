package Characters;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dCollisionList;
import Box2dHelpers.Box2dConversions;
import INPUTS.UserINputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MainCharacter extends GameCharacter implements MovableCharacter {

    UserINputs inputs;
    Animation<TextureRegion> Attacking1Animation;
    Animation<TextureRegion> Attack2Animation;
    Animation<TextureRegion> RunningAnimation;

    boolean direction = false; //true = left , false = right

    float attackingElapsedTime = 0;
    float attacking2ElapsedTime = 0;
    float swordtime = 1.32f;
    float JumpingTime = 0f;

    private Fixture swordFixture;
    private Fixture footFixture;

    public MainCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height, float speed, UserINputs inputs,String FixtureName,int health) {
        super(world, atlas, x, y, width, height,FixtureName,health);
        this.inputs = inputs;
        this.speed = speed;
        Attacking1Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("attacking1"));
        Attack2Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("attacking2"));
        RunningAnimation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("running"));

        MakeFoot(width, height);


        Box2dCollisionList.GiveCollisonBitToBody(bodyfixture, Box2dCollisionList.BIT_CHARACTER);

    }

    public void MakeFoot(int width, int height) {
        //create foot fixture
        FixtureDef foot = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Box2dConversions.unitsToMetres(width) / 2, 0, new Vector2(0, -50 / 200f), 0);
        foot.shape = shape;
        //make it sensor
        foot.isSensor = true;
        foot.filter.categoryBits = Box2dCollisionList.BIT_CHARACTER;
//        foot.filter.maskBits = Box2dCollisionList.BIT_GROUND;


        footFixture = body.createFixture(foot);

        footFixture.setUserData("foot");

    }

    public void MakeSword() {
        Body sword;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(body.getPosition().x + 5 / 200f, body.getPosition().y);

        sword = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(5 / 200f, 5 / 200f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.isSensor = true;


        swordFixture = sword.createFixture(fdef);
        swordFixture.setUserData("sword");

        Box2dCollisionList.GiveCollisonBitToBody(swordFixture, Box2dCollisionList.BIT_SWORD);
        Box2dCollisionList.MakeBodyCollideWith(swordFixture,Box2dCollisionList.BIT_ENEMY);


//        System.out.println(sword.getFixtureList().get(0).getFilterData().categoryBits);
//        System.out.println(sword.getFixtureList().get(0).getFilterData().maskBits);
    }

    @Override
    public void CharacterState(float dt) {
        if (inputs.isRunningRight()) {
            direction = false;
            if (Box2dCollideListeners.playeronGround == false)//body in the air
                this.body.applyForceToCenter(1f, 0, true);
            else
                MoveRight(speed, 0);

            PlayRunningRightAnimation(dt, direction);
        }


        if (inputs.isStanding() && Box2dCollideListeners.playeronGround == true) {
            Stop();
            ResetElapsetTimes();
            ResetFrame(direction);
        }

        if (inputs.isAttacking1()) {
            swordtime += dt;
//            System.out.println("1");
            if (attackingElapsedTime >= 4 / 10f) {//check if the attack is finished or not
                ResetElapsetTimes();
                inputs.Stand();//make Character Stands
                ResetFrame(direction);
                this.MakeSword();
            }

            PLayAttacking1Animation(dt, direction);

        }

        if (inputs.isAttacking2()) {

//            System.out.println("2");
            if (attacking2ElapsedTime >=5 / 10f) {
                ResetElapsetTimes();
                inputs.Stand();
                ResetFrame(direction);
            }
            PlayAttacking2Animation(dt, direction);

        }

        if (inputs.isRunningleft()) {
            direction = true;

            if (Box2dCollideListeners.playeronGround == false)//body in the air
                this.body.applyForceToCenter(-1, 0, true);
            else
                MoveLeft(speed, 0);

            PlayRunningLeftAnimation(dt, direction);

        }

        if (Box2dCollideListeners.playeronGround == true && Gdx.input.isKeyPressed(Input.Keys.UP)) {

            this.Jump();

        }

    }


    private void Stop() {

        body.setLinearVelocity(0, 0);

    }

    public void PlayRunningRightAnimation(float dt, boolean reverse) {

        RunningElapsedTime += dt;
        SetFrameFromAnimation(RunningAnimation, true, RunningElapsedTime, reverse);

    }

    public void PlayRunningLeftAnimation(float dt, boolean reverse) {

        RunningElapsedTime += dt;
        SetFrameFromAnimation(RunningAnimation, true, RunningElapsedTime, reverse);

    }

    public void PLayAttacking1Animation(float dt, boolean reverse) {
        attackingElapsedTime += dt;
        this.SetFrameFromAnimation(Attacking1Animation, false, attackingElapsedTime, reverse);


    }

    public void PlayAttacking2Animation(float dt, boolean reverse) {
        attacking2ElapsedTime += dt;
        this.SetFrameFromAnimation(Attack2Animation, false, attacking2ElapsedTime, reverse);

    }


    private void ResetElapsetTimes() {

        this.attackingElapsedTime = 0;
        this.RunningElapsedTime = 0;
        this.attacking2ElapsedTime = 0;
        this.JumpingTime = 0;

    }

    @Override
    public void update(SpriteBatch batch) {
        super.update(batch);
    }

    public void Jump() {

        this.body.applyForceToCenter(0, 230, true);

    }

    @Override
    public void MoveRight(float speedx, float speedy) {
        body.setLinearVelocity(speed, 0);
    }

    public void MoveLeft(float speedx, float speedy) {
        body.setLinearVelocity(-speed, 0);
    }
}