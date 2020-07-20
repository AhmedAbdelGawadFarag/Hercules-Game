package Characters;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dConversions;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class FlyingCharacters extends GameCharacter implements MovableCharacter {

    boolean flyingLeft = true;
    float firstposotion, secondposition;// two postions that the flying characters go to

    public FlyingCharacters(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName) {
        super(world, atlas, x, y, width, height, FixtureName);
        firstposotion = this.getPosition().x - Box2dConversions.unitsToMetres(100); // the flying caracter travels 100 meters in x axis
        secondposition = this.getPosition().x +  Box2dConversions.unitsToMetres(100);
        body.setGravityScale(0);

    }

    @Override
    public void CharacterState(float dt) {
        System.out.println(this.getPosition().x);
        if (this.getPosition().x <= firstposotion) flyingLeft = false;//travel right
        if (this.getPosition().x >= secondposition) flyingLeft = true;//travel left

        if (flyingLeft == true)
            MoveLeft(1, 0);

        if (flyingLeft == false)
            MoveRight(1, 0);
    }


    @Override
    public void MoveRight(float speedx, float speedy) {

        this.body.setLinearVelocity(speedx, speedy);

    }

    @Override
    public void MoveLeft(float speed, float speedy) {

        this.body.setLinearVelocity(-speed, speedy);

    }
}
