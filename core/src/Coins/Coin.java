package Coins;

import Box2dHelpers.Box2dCollisionList;
import Characters.GameObject;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class Coin extends GameObject {

    float value;

    float elapsedtime=0;

    public Coin(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName,float value) {
        super(world, atlas, x, y, width, height, FixtureName, 1);
        this.value = value;

        Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture, Box2dCollisionList.BIT_COIN);

        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_GROUND);
        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_CHARACTER);
    }

    @Override
    public void CharacterState(float dt) {

    }


}
