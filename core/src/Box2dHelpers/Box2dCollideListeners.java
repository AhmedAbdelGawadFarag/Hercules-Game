package Box2dHelpers;

import Characters.BodyData;
import com.badlogic.gdx.physics.box2d.*;


public class Box2dCollideListeners implements ContactListener {
    public static boolean playeronGround = false;

    @Override
    public void beginContact(Contact contact) {
        // get fixture a and fixture b
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (FootCollidedWithGorund(fa, fb)) {
            playeronGround = true;
            System.out.println("ground");
        }


        checkEnemy(fa,fb);
        checkHercules(fa,fb);

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


    public int GetHealth(Body body) {

        BodyData bd = (BodyData) body.getUserData();
//        System.out.println("healt"+bd.health);
        return bd.health;
    }

    public void decreseHealth(Body body) {
        BodyData bd = (BodyData) body.getUserData();
        bd.decreseHealth();
    }

    public void checkHercules(Fixture fa,Fixture fb) {
        if (fa.getUserData() != null && fa.getUserData() == "hercules" && fb.getUserData() != null && fb.getUserData() == "enemy") {
            decreseHealth(fa.getBody());
        }

        if (fb.getUserData() != null && fb.getUserData() == "hercules" && fa.getUserData() != null && fa.getUserData() == "enemy") {
            decreseHealth(fb.getBody());
        }
    }

    public void checkEnemy(Fixture fa,Fixture fb){
        if (fa.getUserData() != null && fa.getUserData() == "sword") {
            System.out.println("hit");
//            System.out.println(this.GetHealth(fb.getBody()));
            this.decreseHealth(fb.getBody());

        }

        if (fb.getUserData() != null && fb.getUserData() == "sword") {
            System.out.println("hit");
//            System.out.println(this.GetHealth(fa.getBody()));
            this.decreseHealth(fa.getBody());

        }
    }

}
