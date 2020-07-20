package Characters;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BodyData {//the user data that is passed to the body
    public Sprite sprite;//the sprite that the body will have
    public int health;// the health that the body will have

    BodyData(Sprite sprite, int health) {
        this.sprite = sprite;
        this.health = health;
    }
    public void decreseHealth(){
        this.health--;
    }

}
