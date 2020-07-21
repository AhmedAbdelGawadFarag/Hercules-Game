package Characters;

import GameObjects.StaticObjects;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class PunchBag extends StaticObjects {


    public PunchBag(World world, TextureAtlas atlas, float x, float y, int width, int height, String FixtureName, int health) {
        super(world, atlas, x, y, width, height, FixtureName, health);
    }




}
