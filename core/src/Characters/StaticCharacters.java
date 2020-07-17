package Characters;

import INPUTS.UserINputs;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class StaticCharacters extends GameCharacter {


    public StaticCharacters(World world, TextureAtlas atlas, float x, float y, int width, int height) {
        super(world, atlas, x, y, width, height);
    }

    @Override
    public void CharacterState(float dt) {

    }
}
