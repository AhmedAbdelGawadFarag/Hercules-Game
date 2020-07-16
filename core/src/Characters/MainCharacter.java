package Characters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class MainCharacter extends GameCharacter {

   public MainCharacter(World world, TextureAtlas atlas,float x,float y,int width,int height) {
        super(world,atlas,x,y,width,height);
    }

}