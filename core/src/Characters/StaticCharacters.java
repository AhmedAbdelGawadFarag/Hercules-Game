package Characters;

import Box2dHelpers.Box2dCollisionList;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class StaticCharacters extends GameCharacter {


    public StaticCharacters(World world, TextureAtlas atlas, float x, float y, int width, int height) {
        super(world, atlas, x, y, width, height);

        Box2dCollisionList.GiveCollisonBitToBody(body,Box2dCollisionList.BIT_STATIC_CHARACTER);


        Box2dCollisionList.MakeBodyCollideWith(body,Box2dCollisionList.BIT_GROUND);

        Box2dCollisionList.MakeBodyCollideWith(body,Box2dCollisionList.BIT_CHARACTER);


            Box2dCollisionList.GiveFriction(body,100);

    }

    @Override
    public void CharacterState(float dt) {

    }



}
