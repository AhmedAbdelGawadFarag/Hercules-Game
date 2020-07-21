package Characters;

import GameObjects.StaticObjects;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class Pillars extends StaticObjects {

    Animation<TextureRegion> dyingAnimation;

    float elapsedTime = 0;

    public Pillars(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, int health) {
        super(world, atlas, x, y, width, height, FixtureName, health);
        dyingAnimation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("pillar"));
    }

    @Override
    public void CharacterState(float dt) {
        super.CharacterState(dt);

        if (getHealthFromBody() <= 0) {
            System.out.println(elapsedTime);
            elapsedTime += dt;
            playDistrcutAnimation(elapsedTime, false);
        }


    }


    protected boolean isDead() {
        return (getHealthFromBody() == 0 && isAnimationPlayed());

    }

    private boolean isAnimationPlayed() {
        return elapsedTime >= 5 / 10f;
    }

    private void playDistrcutAnimation(float dt, boolean reverse) {

        this.SetFrameFromAnimation(dyingAnimation, false, dt, reverse);

    }


}
