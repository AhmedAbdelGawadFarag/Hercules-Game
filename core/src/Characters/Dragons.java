package Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class Dragons extends FlyingCharacters {

    private Animation<TextureRegion> flyingAnimation;
    private float flyingElapsedTime = 0;

    public Dragons(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, float DistanceTravle) {
        super(world, atlas, x, y, width, height, FixtureName, DistanceTravle);
        flyingAnimation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("flying"));

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
