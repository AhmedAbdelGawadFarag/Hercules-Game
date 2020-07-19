package map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import mapObjects.Ground;

public class Level1Map {
	private final TmxMapLoader mapLoader;
	private final TiledMap map;
	private final OrthogonalTiledMapRenderer renderer;
	private final OrthographicCamera camera;
	World world;
	Box2DDebugRenderer b2dr;
	public Level1Map(OrthographicCamera camera,World world) {
		// TODO Auto-generated constructor stub
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("Map/lvl1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map,1f/200f);
		this.camera= camera;
		this.world = world;
	
		getObjectsFromTheMap();
		
	}
	public void getObjectsFromTheMap() {
		b2dr = new Box2DDebugRenderer();
		for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
			new Ground(world,object);
		}
		

	}
	public void update() {
//		camera.update();
		renderer.setView(camera);
		b2dr.render(world, camera.combined);
		renderer.render();
	}
}
