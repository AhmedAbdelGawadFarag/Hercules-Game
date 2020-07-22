package Characters;

import Box2dHelpers.Box2dCollisionList;
import GameObjects.MovingObjects;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class WoodMonster extends MovingObjects {

    private final Animation<TextureRegion> movingAnimation;
    private float flyingElapsedTime = 0;

    public WoodMonster(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, float DistanceTravle, int health) {
        super(world, atlas, x, y, width, height, FixtureName, DistanceTravle, health, 9.8f);

        Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture, Box2dCollisionList.BIT_ENEMY);

        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_SWORD);
        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_GROUND);
        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_CHARACTER);

        System.out.println(this.bodyfixture.getFilterData().maskBits);

        movingAnimation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("wood"));

    }

    public void CharacterState(float dt) {
        super.CharacterState(dt);

        flyingElapsedTime += dt;

        playFlyingAnimation(flyingElapsedTime, this.flyingLeft == true);


    }

    private void playFlyingAnimation(float dt, boolean reverse) {
        this.SetFrameFromAnimation(movingAnimation, true, dt, reverse);
    }


}
