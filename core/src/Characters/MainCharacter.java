package Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainCharacter extends GameCharacter implements InputProcessor {
    public boolean isPressed = false;//boolean to check if key is just still pressed or no


    public MainCharacter(TextureAtlas atlas) {
        super(atlas);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void Move() {

        if (moveright == true)
            MoveRight();

        if (moveleft == true)
            MoveLeft();

    }

    @Override
    void MoveRight() {
        //move right
        sprite.setPosition(sprite.getX() + (200 * Gdx.graphics.getDeltaTime()), sprite.getY());
        currframe++;

        if (currframe > 7) currframe = 1;

        this.sprite.setRegion(this.atlas.findRegion(String.format("00%d", currframe)));

    }

    @Override
    void MoveLeft() {


    }


    @Override
    public boolean keyDown(int keycode) {
        this.isPressed = true;

        if (keycode == Input.Keys.RIGHT)
            this.moveright = true;

        if (keycode == Input.Keys.RIGHT)
            this.moveleft = true;


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
       stopMoving();
        this.isPressed =false;
        this.moveright = false;
        this.moveleft = false;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
