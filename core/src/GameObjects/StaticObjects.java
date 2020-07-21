package GameObjects;

import Box2dHelpers.Box2dCollisionList;
import GameObjects.GameObject;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class StaticObjects extends GameObject {


    public StaticObjects(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, int health) {
        super(world, atlas, x, y, width, height,FixtureName,health);


        Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture, Box2dCollisionList.BIT_ENEMY);
        this.body.setType(BodyDef.BodyType.StaticBody);
    }

    @Override
    public void CharacterState(float dt) {

    }


}
