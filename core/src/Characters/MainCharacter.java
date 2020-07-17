package Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class MainCharacter extends GameCharacter implements MovableCharacter {

    UserINputs inputs;
    Animation<TextureRegion> Attacking1Animation;
    Animation<TextureRegion> Attack2Animation;

    float attackingElapsedTime = 0;
    float attacking2ElapsedTime = 0;

    public MainCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height, float speed, UserINputs inputs) {
        super(world, atlas, x, y, width, height);
        this.inputs = inputs;
        this.speed = speed;
        Attacking1Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("attacking1"));
        Attack2Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("secondAttack"));

    }


    @Override
    public void CharacterState(float dt) {
        if (inputs.isRunning()) {
            Move();
            PlayRunningAnimation(dt);
        }

        if (inputs.isStanding()) {
            Stop();
            ResetElapsetTimes();
            ResetFrame();
        }
        if (inputs.isAttacking1()) {
            System.out.println("1");
            if (attackingElapsedTime >= 4 / 10f) {//check if the attack is finished or not
                ResetElapsetTimes();
                inputs.Stand();//make Character Stands
                ResetFrame();
            }
            PLayAttacking1Animation(dt);

        }

        if (inputs.isAttacking2()) {

            System.out.println("2");
            if (attacking2ElapsedTime >= 3 / 10f) {
                ResetElapsetTimes();
                inputs.Stand();
                ResetFrame();
            }
            PlayAttacking2Animation(dt);

        }


    }


    private void Stop() {

        body.setLinearVelocity(0, 0);

    }

    public void PLayAttacking1Animation(float dt) {
        attackingElapsedTime += dt;
        this.SetFrame(Attacking1Animation, false, attackingElapsedTime);

    }

    public void PlayAttacking2Animation(float dt) {
        attacking2ElapsedTime += dt;
        this.SetFrame(Attack2Animation, false, attacking2ElapsedTime);

    }


    private void ResetElapsetTimes() {

        this.attackingElapsedTime = 0;
        this.RunningElapsedTime = 0;
        this.attacking2ElapsedTime = 0;

    }

    @Override
    public void Move() {
        body.setLinearVelocity(speed,0);
    }
}