package Box2dHelpers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Box2dCollisionList {


    public static final short BIT_CHARACTER = 2;

    public static final short BIT_STANDING_CHARACTER = 8;

    public static final short BIT_SWORD = 4;

    public static final short BIT_FLOOR = 16;


    public static void GiveCollisonBitToBody(Body body, short bit) {

        Fixture f = body.getFixtureList().get(0);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = f.getShape();

        fdef.filter.categoryBits = bit;
        fdef.filter.maskBits = f.getFilterData().maskBits;

        body.createFixture(fdef);

        body.destroyFixture(f);
    }

    public static void MakeBodyCollideWith(Body body, short bit) {

        Fixture f = body.getFixtureList().get(0);
        FixtureDef fdef = new FixtureDef();

        fdef.shape = f.getShape();

        fdef.filter.categoryBits = f.getFilterData().categoryBits;

        fdef.filter.maskBits = (f.getFilterData().maskBits);

        if (fdef.filter.maskBits == -1)
            fdef.filter.maskBits = 0;

        fdef.filter.maskBits |= (short) bit;

        body.createFixture(fdef);

        body.destroyFixture(f);

    }


}
