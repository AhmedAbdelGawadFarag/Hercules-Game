package Characters;

import Box2dHelpers.Box2dCollisionList;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class Dragons extends FlyingCharacters {

    private Animation<TextureRegion> flyingAnimation;
    private float flyingElapsedTime = 0;

    public Dragons(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, float DistanceTravle,int health) {
        super(world, atlas, x, y, width, height, FixtureName, DistanceTravle,health);
        flyingAnimation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("flying"));

        Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture, Box2dCollisionList.BIT_ENEMY);

        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_SWORD);
        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_GROUND);
        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_CHARACTER);
    }

    @Override
    public void CharacterState(float dt) {
        super.CharacterState(dt);

        flyingElapsedTime+=dt;

        if (this.flyingLeft == true)
            playFlyingAnimation(flyingElapsedTime, true);
        else
            playFlyingAnimation(flyingElapsedTime, false);


    }

    private void playFlyingAnimation(float dt, boolean reverse) {
        this.SetFrameFromAnimation(flyingAnimation, true, dt, reverse);
    }


}
