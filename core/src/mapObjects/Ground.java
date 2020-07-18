package mapObjects;

import Box2dHelpers.Box2dCollisionList;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import Box2dHelpers.Box2dConversions;

import com.badlogic.gdx.physics.box2d.World;

public class Ground {
	 public World world;
	 public Body body;
	public Ground(World world,MapObject object) {
		this.world = world;
		makeObject(object);
	}
	private void makeObject(MapObject object) {
		
		BodyDef def = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fDef = new FixtureDef();
		def.type = BodyType.StaticBody;

		Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

		def.position.set(Box2dConversions.unitsToMetres(rectangle.getX() + rectangle.getWidth()/2),Box2dConversions.unitsToMetres( rectangle.getY()+rectangle.getHeight()/2));
		body = world.createBody(def);

		shape.setAsBox(Box2dConversions.unitsToMetres(rectangle.getWidth()/2),Box2dConversions.unitsToMetres(rectangle.getHeight()/2));

		fDef.shape = shape;
		body.createFixture(fDef);

		Box2dCollisionList.GiveCollisonBitToBody(body,Box2dCollisionList.BIT_GROUND);


	}
}
