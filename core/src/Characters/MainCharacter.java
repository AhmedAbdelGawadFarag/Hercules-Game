package Characters;

import Box2dHelpers.Box2dCollisionList;
import INPUTS.UserINputs;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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


    public MainCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height, float speed, UserINputs inputs) {
        super(world, atlas, x, y, width, height);
        this.inputs = inputs;
        this.speed = speed;
        Attacking1Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("attacking1"));
        Attack2Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("secondAttack"));
        RunningAnimation = new Animation<TextureRegion>(1 / 20f, atlas.findRegions("running"));

        Fixture f = body.getFixtureList().get(0);

        Box2dCollisionList.GiveCollisonBitToBody(body, Box2dCollisionList.BIT_CHARACTER);

//        Box2dCollisionList.MakeBodyCollideWith(body, Box2dCollisionList.);


    }

    public void MakeSword() {
        Body sword;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(body.getPosition().x + 40/200f, body.getPosition().y);

        sword = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(10 / 200f, 10 / 200f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        sword.createFixture(fdef);

        Box2dCollisionList.GiveCollisonBitToBody(sword, Box2dCollisionList.BIT_SWORD);
        Box2dCollisionList.MakeBodyCollideWith(sword, Box2dCollisionList.BIT_STANDING_CHARACTER);


    }

    @Override
    public void CharacterState(float dt) {
        if (inputs.isRunningRight()) {
            direction = false;
            MoveRight();
            PlayRunningRightAnimation(dt, direction);

        }

        if (inputs.isStanding()) {
            Stop();
            ResetElapsetTimes();
            ResetFrame(direction);

        }
        if (inputs.isAttacking1()) {
            swordtime += dt;
            System.out.println("1");
            if (attackingElapsedTime >= 4 / 10f) {//check if the attack is finished or not
                ResetElapsetTimes();
                inputs.Stand();//make Character Stands
                ResetFrame(direction);
                this.MakeSword();
            }

            PLayAttacking1Animation(dt, direction);

        }

        if (inputs.isAttacking2()) {

            System.out.println("2");
            if (attacking2ElapsedTime >= 3 / 10f) {
                ResetElapsetTimes();
                inputs.Stand();
                ResetFrame(direction);
            }
            PlayAttacking2Animation(dt, direction);

        }

        if (inputs.isRunningleft()) {
            direction = true;
            MoveLeft();
            PlayRunningLeftAnimation(dt, direction);

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

    }

    @Override
    public void update(SpriteBatch batch) {
        super.update(batch);

//        sword.setLinearVelocity(1,0);
//        sword.setTransform(body.getPosition().x + 100/200f, body.getPosition().y - 20 / 200f, 1.32f);


//        System.out.println(sword.getFixtureList().get(0).getFilterData().categoryBits + " " + sword.getFixtureList().get(0).getFilterData().maskBits);

    }


    @Override
    public void MoveRight() {
        body.setLinearVelocity(speed, 0);
    }

    public void MoveLeft() {
        body.setLinearVelocity(-speed, 0);
    }
}