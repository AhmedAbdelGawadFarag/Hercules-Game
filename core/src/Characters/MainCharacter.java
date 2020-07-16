package Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class MainCharacter extends GameCharacter {

    UserINputs inputs;
    Animation<TextureRegion> AttackingAnimation;
    Animation<TextureRegion> Attack2Animation;

    float attackingElapsedTime = 0;
    float attacking2ElapsedTime = 0;

    public MainCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height, float speed, UserINputs inputs) {
        super(world, atlas, x, y, width, height, speed);
        this.inputs = inputs;

        AttackingAnimation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("attacking1"));
        Attack2Animation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("secondAttack"));

    }


    @Override
    public void CharacterState(float dt) {
        if (inputs.isRunning()) {
            PlayRunningAnimation(dt);
        }

        if (inputs.isStanding()) {
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
            PLayAttackingAnimation(dt);

        }

        if (inputs.isAttacking2()) {

            System.out.println("2");
            if (attacking2ElapsedTime >= 3/10f) {
                ResetElapsetTimes();
                inputs.Stand();
                ResetFrame();
            }
            PlayAttacking2Animation(dt);

        }


    }

    public void MoveRight() {
        body.setLinearVelocity(1, 0);
    }

    public void PLayAttackingAnimation(float dt) {
        attackingElapsedTime += dt;
        this.SetFrame(AttackingAnimation, false, attackingElapsedTime);

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

}