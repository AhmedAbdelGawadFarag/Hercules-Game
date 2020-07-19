package Box2dHelpers;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;


public class Box2dCollideListeners implements ContactListener {
    public static boolean playeronGround = false;
    private static ArrayList<Body> BodiesToRemove = new ArrayList<Body>();

    @Override
    public void beginContact(Contact contact) {
        // get fixture a and fixture b
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (FootCollidedWithGorund(fa, fb)) {
            playeronGround = true;
            System.out.println("ground");
        }

        if (fa.getUserData() != null && fa.getUserData() == "sword") {
            System.out.println("hit");
            BodiesToRemove.add(fb.getBody());
        }

        if (fb.getUserData() != null && fb.getUserData() == "sword") {
            System.out.println("hit");
            BodiesToRemove.add(fa.getBody());
        }


    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (FootCollidedWithGorund(fa, fb)) {
            playeronGround = false;


            System.out.println("air");


        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean FootCollidedWithGorund(Fixture fa, Fixture fb) {
        if (fa.getUserData() != null && fa.getUserData().equals("foot")) {
            return true;
        }

        return fb.getUserData() != null && fb.getUserData().equals("foot");
    }

    public static ArrayList<Body> getBodiesToRemove() {
        return BodiesToRemove;
    }
}
