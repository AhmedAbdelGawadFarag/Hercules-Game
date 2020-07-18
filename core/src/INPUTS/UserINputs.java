package INPUTS;

import Box2dHelpers.Box2dCollideListeners;
import Box2dHelpers.Box2dConversions;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import javax.swing.*;


public class UserINputs implements InputProcessor {
    protected boolean runningRight = false;
    protected boolean standing = false;
    protected boolean attacking1 = false;
    protected boolean attacking2 = false;
    protected boolean runningleft = false;



    public boolean isRunningRight() {
        return runningRight;
    }

    public boolean isRunningleft() {
        return runningleft;
    }




    public void RunRight() {
        this.runningRight = true;
        this.runningleft = false;
        this.standing = false;
        this.attacking1 = false;
        this.attacking2 = false;
    }

    public void RunLeft() {
        this.runningleft = true;
        this.runningRight = false;
        this.standing = false;
        this.attacking1 = false;
        this.attacking2 = false;
    }

    public boolean isStanding() {
        return standing;
    }

    public void Stand() {
        this.standing = true;
        this.runningRight = false;
        this.attacking1 = false;
        this.attacking2 = false;
        this.runningleft = false;

    }

    public boolean isAttacking1() {
        return attacking1;
    }

    public void Attack1() {
        this.attacking1 = true;
        this.standing = false;
        this.runningRight = false;
        this.attacking2 = false;
        this.runningleft = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            RunRight();
        }

        if (keycode == Input.Keys.LEFT) {
            RunLeft();
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE)
            Attack1();

        if (keycode == Input.Keys.A)
            Attack2();



        if (attacking1 != true && attacking2 != true){   //if hero is not stillattacking
            Stand();
        }

        return false;
    }

    public boolean isAttacking2() {
        return attacking2;
    }


    public void Attack2() {
        this.attacking2 = true;
        this.standing = false;
        this.runningRight = false;
        this.attacking1 = false;
        this.runningleft = false;
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
