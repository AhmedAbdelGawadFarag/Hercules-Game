package Box2dHelpers;

public abstract class Box2dConversions {
    static float ppm = 200; //pixel per meters

    public static float unitsToMetres(float units) {
        return units / ppm;
    }

    public static float metresToUnits(float metres) {
        return metres * ppm;
    }


}
