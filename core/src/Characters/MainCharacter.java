package Characters;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dCollisionList;
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


    public MainCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height, float speed, UserINputs inputs) {
        super(world, atlas, x, y, width, height);
        this.inputs = inputs;
        this.speed = speed;
        Attacking1Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("attacking1"));
        Attack2Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("secondAttack"));
        RunningAnimation = new Animation<TextureRegion>(1 / 20f, atlas.findRegions("running"));

        MakeFoot(height);


        Box2dCollisionList.GiveCollisonBitToBody(body, Box2dCollisionList.BIT_CHARACTER);

    }

    public void MakeFoot(int height) {
        //create foot fixture
        FixtureDef foot = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2f / 200f, 3f / 200f, new Vector2(0, -40 / 200f), 0);
        foot.shape = shape;
        //make it sensor
        foot.isSensor = true;

        foot.filter.categoryBits = Box2dCollisionList.BIT_CHARACTER;
        foot.filter.maskBits = Box2dCollisionList.BIT_GROUND;

        body.createFixture(foot).setUserData("foot");
    }

    public void MakeSword() {
        Body sword;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(body.getPosition().x + 30 / 200f, body.getPosition().y);

        sword = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(10 / 200f, 10 / 200f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        fdef.isSensor = true;

        sword.createFixture(fdef).setUserData("sword");

        Box2dCollisionList.GiveCollisonBitToBody(sword, Box2dCollisionList.BIT_SWORD);
        Box2dCollisionList.MakeBodyCollideWith(sword, Box2dCollisionList.BIT_STATIC_CHARACTER);
        Box2dCollisionList.MakeBodyCollideWith(sword, Box2dCollisionList.BIT_GROUND);

    }

    @Override
    public void CharacterState(float dt) {
        if (inputs.isRunningRight()) {
            direction = false;
            if (Box2dCollideListeners.playeronGround == false)//body in the air
                this.body.applyForceToCenter(1f, 0, true);
            else
                MoveRight(speed,0);

            PlayRunningRightAnimation(dt, direction);
        }

        if (inputs.isStanding() && Box2dCollideListeners.playeronGround == true) {
            System.out.println(inputs.isStanding());
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
            if (attacking2ElapsedTime >= 3 / 10f) {
                ResetElapsetTimes();
                inputs.Stand();
                ResetFrame(direction);
            }
            PlayAttacking2Animation(dt, direction);

        }

        if (inputs.isRunningleft()) {
            direction = true;

            if (Box2dCollideListeners.playeronGround == false)//body in the air
                this.body.applyForceToCenter(1, 0, true);
            else
                MoveLeft(speed,0);

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

        this.body.applyForceToCenter(0, 300, true);

    }

    @Override
    public void MoveRight(float speedx,float speedy) {
        body.setLinearVelocity(speed, 0);
    }

    public void MoveLeft(float speedx,float speedy) {
        body.setLinearVelocity(-speed, 0);
    }
}