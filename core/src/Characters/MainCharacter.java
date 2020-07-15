package Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainCharacter extends GameCharacter{
    public boolean isPressed = false;//boolean to check if key is just still pressed or no


    public MainCharacter(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public void Move() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            MoveRight();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            MoveLeft();
    }

    @Override
    void MoveRight() {
        //move right

        currframe++;

        if (currframe > 7) currframe = 1;

        this.sprite.setRegion(this.atlas.findRegion(String.format("00%d", currframe)));

    }

    @Override
    void MoveLeft() {

    }






}
