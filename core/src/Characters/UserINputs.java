package Characters;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class UserINputs implements InputProcessor {
    protected boolean running = false;
    protected boolean standing = false;
    protected boolean attacking1 = false;
    protected boolean attacking2 = false;

    public boolean isRunning() {
        return running;
    }

    public void Run() {
        this.running = true;
        this.standing = false;
        this.attacking1 = false;
        this.attacking2 = false;
    }

    public boolean isStanding() {
        return standing;
    }

    public void Stand() {
        this.standing = true;
        this.running = false;
        this.attacking1 = false;
        this.attacking2 = false;
    }

    public boolean isAttacking1() {
        return attacking1;
    }

    public void Attack1() {
        this.attacking1 = true;
        this.standing = false;
        this.running = false;
        this.attacking2 = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            Run();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE)
            Attack1();

        if (keycode == Input.Keys.A)
            Attack2();

        if (attacking1 != true && attacking2 != true)//if hero is not stillattacking
            Stand();

        return false;
    }

    public boolean isAttacking2() {
        return attacking2;
    }

    public void Attack2() {
        this.attacking2 = true;
        this.standing = false;
        this.running = false;
        this.attacking1 = false;
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
