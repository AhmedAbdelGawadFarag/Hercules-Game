package Characters;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class UserINputs implements InputProcessor {
    boolean running = false;
    boolean standing = false;
    boolean attacking = false;

    public boolean isRunning() {
        return running;
    }

    public void Run() {
        this.running = true;
        this.standing = false;
        this.attacking = false;
    }

    public boolean isStanding() {
        return standing;
    }

    public void Stand() {
        this.standing = true;
        this.running = false;
        this.attacking = false;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void Attack() {
        this.attacking = true;
        this.standing = false;
        this.running = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            Run();
        }
        if (keycode == Input.Keys.SPACE) {
            Attack();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Stand();
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
