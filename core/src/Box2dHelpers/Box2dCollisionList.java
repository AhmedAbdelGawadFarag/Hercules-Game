package Box2dHelpers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Box2dCollisionList {


    public static final short BIT_CHARACTER = 2;

    public static final short BIT_ENEMY = 8;

    public static final short BIT_SWORD = 4;

    public static final short BIT_GROUND = 16;

    public static final short BIT_COIN = 32;


    public static void GiveCollisonBitToBody(Fixture fixture, short bit) {
        Filter filter = fixture.getFilterData();

        filter.categoryBits = bit;
        fixture.setFilterData(filter);

    }

    public static void MakeBodyCollideWith(Fixture fixture, short bit) {
        Filter filter = fixture.getFilterData();

        final short temp = filter.maskBits;
        if (temp == -1)
            filter.maskBits = bit;
        else {
            filter.maskBits |=bit;
        }

        fixture.setFilterData(filter);

    }


}
