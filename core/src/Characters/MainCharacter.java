package Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainCharacter extends GameCharacter implements InputProcessor {


    public MainCharacter(TextureAtlas atlas) {
        super(atlas);
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.RIGHT) {//move right

            currframe++;

            if (currframe > 7) currframe = 1;

            this.sprite.setRegion( this.atlas.findRegion(String.format("00%d",currframe)));

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
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
