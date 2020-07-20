package Characters;

import Box2dHelpers.Box2dCollisionList;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class StaticCharacters extends GameCharacter {


    public StaticCharacters(World world, TextureAtlas atlas, float x, float y, int width, int height,String FixtureName,int health) {
        super(world, atlas, x, y, width, height,FixtureName,health);


        Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture, Box2dCollisionList.BIT_ENEMY);

        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_SWORD);
        Box2dCollisionList.MakeBodyCollideWith(this.bodyfixture, Box2dCollisionList.BIT_GROUND);

        System.out.println(this.bodyfixture.getUserData());

    }

    @Override
    public void CharacterState(float dt) {

    }


}
