package Characters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

import Box2dHelpers.Box2dCollisionList;
import GameObjects.StaticObjects;

public class EnergyDrink extends StaticObjects {
	public EnergyDrink(World world,TextureAtlas atlas,float x,float y, int width,int height, String FixtureName) {
		super(world, atlas, x, y, width, height, FixtureName, 1);
		Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture,Box2dCollisionList.BIT_ENERGY);
		Box2dCollisionList.GiveCollisonBitToBody(this.bodyfixture, Box2dCollisionList.BIT_CHARACTER);
	}
	
}
