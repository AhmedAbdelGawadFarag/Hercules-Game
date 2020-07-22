package Characters;

import Coins.GoldCoin;
import Coins.SilverCoin;
import GameObjects.GameObject;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class MakeObjects {
    World world;

    public MakeObjects(World world) {
        this.world = world;
    }

    public void MakePillars(ArrayList<GameObject> staticCaracters) {
        staticCaracters.add(new Pillars(world, new TextureAtlas("pillars/Main.atlas"), 1209.223877f, 235.138458f, 50, 150, "stat", 3));
        staticCaracters.add(new Pillars(world, new TextureAtlas("pillars/Main.atlas"), 1201.237061f, 234.999939f, 50, 150, "stat", 3));
        staticCaracters.add(new Pillars(world, new TextureAtlas("pillars/Main.atlas"), 2935.314453f, 250.999954f, 50, 150, "stat", 3));
        staticCaracters.add(new Pillars(world, new TextureAtlas("pillars/Main.atlas"), 12737.140625f, 382.610626f, 50, 150, "stat", 3));


    }

    public void MakeCoins(ArrayList<GameObject> coins) {
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 351.508484f, 106.749977f, 40, 80, "silverCoin", 100));
        coins.add(new GoldCoin(world, new TextureAtlas("coins/goldCoins/Main.atlas"), 438.568695f, 106.749977f, 40, 80, "goldCoin", 200));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 946.390320f, 106.749977f, 40, 80, "silverCoin", 100));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 4012.837646f, 442.999939f, 40, 80, "silverCoin", 100));
        coins.add(new GoldCoin(world, new TextureAtlas("coins/goldCoins/Main.atlas"), 4309.497559f, 442.999939f, 40, 80, "goldCoin", 200));
        coins.add(new GoldCoin(world, new TextureAtlas("coins/goldCoins/Main.atlas"), 5206.455566f, 462.222290f, 40, 80, "goldCoin", 200));
        coins.add(new GoldCoin(world, new TextureAtlas("coins/goldCoins/Main.atlas"), 7504.650879f, 492.333221f, 40, 80, "goldCoin", 200));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 10877.237305f, 266.999969f, 40, 80, "silverCoin", 100));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 11108.647461f, 266.332977f, 40, 80, "silverCoin", 100));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 11377.586914f, 268.332947f, 40, 80, "silverCoin", 100));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 11639.959961f, 268.332855f, 40, 80, "silverCoin", 100));
        coins.add(new SilverCoin(world, new TextureAtlas("coins/silverCoins/Main.atlas"), 11899.855469f, 269.666992f, 40, 80, "silverCoin", 100));
        coins.add(new GoldCoin(world, new TextureAtlas("coins/goldCoins/Main.atlas"), 12128.162109f, 266.999969f, 40, 80, "goldCoin", 200));
        coins.add(new GoldCoin(world, new TextureAtlas("coins/goldCoins/Main.atlas"), 15906.996094f, 524.333313f, 40, 80, "goldCoin", 200));

    }

    public void MakeWoodenMonsters(ArrayList<GameObject> enemies) {

        enemies.add(new WoodMonster(world, new TextureAtlas("woodMonster/Main.atlas"), 888.110535f, 106.749977f, 40, 80, "enemy", 100, 2));
        enemies.add(new WoodMonster(world, new TextureAtlas("woodMonster/Main.atlas"), 2094.128662f, 122.999969f, 40, 80, "enemy", 100, 2));
        enemies.add(new WoodMonster(world, new TextureAtlas("woodMonster/Main.atlas"), 4781.038574f, 233.666946f, 40, 80, "enemy", 100, 2));
        enemies.add(new WoodMonster(world, new TextureAtlas("woodMonster/Main.atlas"), 6323.907227f, 109.666969f, 40, 80, "enemy", 100, 2));
        enemies.add(new WoodMonster(world, new TextureAtlas("woodMonster/Main.atlas"), 9998.890625f, 378.999969f, 40, 80, "enemy", 100, 2));


    }

    public void MakeDragons(ArrayList<GameObject> enemies) {
        enemies.add(new Dragon(world, new TextureAtlas("dragons/Main.atlas"), 1696.988159f, 234.999939f, 40, 100, "enemy", 120, 2));
        enemies.add(new Dragon(world, new TextureAtlas("dragons/Main.atlas"), 5701.878906f, 298.999939f, 40, 100, "enemy", 120, 2));
        enemies.add(new Dragon(world, new TextureAtlas("dragons/Main.atlas"), 8193.279297f, 107.633575f, 40, 100, "enemy", 120, 2));
    }

}
