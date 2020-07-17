package Screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

abstract class CameraManager {

    public static void LockOnTarger(Camera cam, Vector2 target) {
        cam.position.set(target, 0);
    }


}
