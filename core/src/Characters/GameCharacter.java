package Characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public abstract class GameCharacter {
    public World world;
    public Body body;
    TextureAtlas atlas;

    public GameCharacter(World world, TextureAtlas atlas, float x, float y,int width,int height) {
        TextureRegion reg = (atlas.findRegion("running"));
        this.atlas = atlas;
        this.world = world;

        makeCharacter(x, y,width,height);

        Sprite temp = new Sprite(reg);
        temp.setSize(width / 100f, height / 100f);


        body.setUserData(temp);

    }

    public void makeCharacter(float x, float y,int width,int height) {//dynamic character
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        x = Box2dConversions.unitsToMetres(x);//convert x to meters
        y = Box2dConversions.unitsToMetres(y);// conver y to approprotaie meters

        def.position.set(x, y);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Box2dConversions.unitsToMetres(width), Box2dConversions.unitsToMetres(height));


        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        body.createFixture(fdef);

    }

    public void update(SpriteBatch batch) {
        Sprite sprite = (Sprite) body.getUserData();

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2f, body.getPosition().y - sprite.getHeight() / 2f);
        sprite.draw(batch);
    }



}