package Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainCharacter extends GameCharacter implements InputProcessor {
    public boolean isPressed = false;//boolean to check if key is just still pressed or no
    int attackframe = 8;
    public boolean CanAttack = false;

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

        if (currframe > 7) currframe = 4;

        this.sprite.setRegion(this.atlas.findRegion(String.format("00%d", currframe)));

    }

    @Override
    void MoveLeft() {


    }

    @Override
    public void attack() {
        if (CanAttack) {
            attackframe++;
            if (attackframe > 12) attackframe = 8;

            String t = "";
            if (attackframe >= 10) t = String.format("0%d", attackframe);
            else t = String.format("00%d",attackframe);

            this.sprite.setRegion(this.atlas.findRegion(t));

        }

    }


    @Override
    public boolean keyDown(int keycode) {
        this.isPressed = true;

        if (keycode == Input.Keys.RIGHT)
            this.moveright = true;

        if (keycode == Input.Keys.RIGHT)
            this.moveleft = true;

        if(keycode==Input.Keys.SPACE)
            this.CanAttack = true;


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        stopMoving();
        this.isPressed = false;
        this.moveright = false;
        this.moveleft = false;
        this.CanAttack = false;



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
