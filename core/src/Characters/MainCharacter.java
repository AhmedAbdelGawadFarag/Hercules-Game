package Characters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class MainCharacter extends GameCharacter {

    UserINputs inputs;

    public MainCharacter(World world, TextureAtlas atlas, float x, float y, int width, int height, float speed, UserINputs inputs) {
        super(world, atlas, x, y, width, height, speed);
        this.inputs = inputs;
    }

    @Override
    void changeAnimation(float dt) {


    }

    @Override
    public void CharacterState(float dt) {
        if (inputs.isRunning()) {
           PlayRunningAnimation(dt);
        }

        if(inputs.isStanding()){
            ResetFrame();
        }

    }

    public void MoveRight() {
        body.setLinearVelocity(1,0);
    }

}