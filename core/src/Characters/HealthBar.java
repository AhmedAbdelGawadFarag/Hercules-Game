package Characters;

import Box2dHelpers.Box2dConversions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class HealthBar {
    public Sprite sp;
    public TextureAtlas atlas;

    public HealthBar(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public void draw(SpriteBatch batch, Vector2 pos, GameCharacter Herecules) {
        sp = new Sprite(atlas.findRegion("health", Math.abs(Herecules.getHealthFromBody() - 3)));

        sp.setPosition(pos.x - Box2dConversions.unitsToMetres(600), Box2dConversions.unitsToMetres(Gdx.graphics.getHeight() - 600));
        sp.setSize(Box2dConversions.unitsToMetres(50), Box2dConversions.unitsToMetres(30));
        sp.draw(batch);
    }


}
