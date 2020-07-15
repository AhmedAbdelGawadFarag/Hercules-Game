package Characters;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameCharacter{
    protected TextureAtlas atlas;
    protected Sprite sprite;
    protected TextureRegion region;
    int currframe; //each char have multiframe
    public boolean moveright = false;
    public boolean moveleft = false;

    GameCharacter(TextureAtlas atlas) {
        this.atlas = atlas;
        this.region = atlas.findRegion("001");
        this.sprite = new Sprite(region);
        this.currframe = 1;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);

    }

    abstract public  void Move();
    abstract void MoveRight();
    abstract void MoveLeft();
    void stopMoving(){
        currframe = 1;
        this.sprite.setRegion(this.atlas.findRegion(String.format("00%d", currframe)));
    }

}