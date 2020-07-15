package Characters;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameCharacter{
    protected TextureAtlas atlas;
    protected Sprite sprite;
    protected TextureRegion region;
    int currframe; //each char have multiframe

    GameCharacter(TextureAtlas atlas) {
        this.atlas = atlas;
        this.region = atlas.findRegion("001");
        this.sprite = new Sprite(region);
        this.currframe = 1;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


}
