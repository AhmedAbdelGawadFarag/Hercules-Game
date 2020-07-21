package Coins;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class SilverCoin extends Coin {

    private final Animation<TextureRegion> rotation;


    public SilverCoin(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, float value) {
        super(world, atlas, x, y, width, height, FixtureName, value);
        rotation = new Animation<TextureRegion>(1 / 10f, atlas.findRegions("silver"));
    }

    @Override
    public void CharacterState(float dt) {
        this.elapsedtime += dt;
        super.CharacterState(dt);
        playAnimation(elapsedtime);
    }

    private void playAnimation(float dt) {
        this.SetFrameFromAnimation(rotation, true, dt, false);
    }


}
