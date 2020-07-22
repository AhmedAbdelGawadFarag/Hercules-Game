package Characters;

import Coins.GoldCoin;
import Coins.SilverCoin;
import GameObjects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class MakeEnemies {
    World world;
    GameObject Herecules;

    public MakeEnemies(World world, GameObject Herecules) {
        this.world = world;
        this.Herecules = Herecules;
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            System.out.printf("staticCaracters.add(new Pillars(world, new TextureAtlas(\"pillars/Main.atlas\"), %f, %f, 30, 80, \"stat\", 3));\n", Herecules.getPosition().x * 200f, Herecules.getPosition().y * 200f);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            System.out.printf("enemies.add(new WoodMonster(world, new TextureAtlas(\"woodMonster/Main.atlas\"), %f, %f, 40, 80, \"enemy\", 200, 2));\n", Herecules.getPosition().x * 200f, Herecules.getPosition().y * 200f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            System.out.printf("enemies.add(new Dragon(world, new TextureAtlas(\"dragons/Main.atlas\"), %f, %f, 40, 80, \"enemy\", 200, 2));\n", Herecules.getPosition().x * 200f, Herecules.getPosition().y * 200f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.G)){
            System.out.printf("coins.add(new GoldCoin(world, new TextureAtlas(\"coins/goldCoins/Main.atlas\"), %f, %f, 40, 80, \"goldCoin\", 200));\n", Herecules.getPosition().x * 200f, Herecules.getPosition().y * 200f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            System.out.printf("coins.add(new SilverCoin(world, new TextureAtlas(\"coins/silverCoins/Main.atlas\"), %f, %f, 40, 80, \"silverCoin\", 100));\n",Herecules.getPosition().x * 200f, Herecules.getPosition().y * 200f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            System.out.printf("staticCaracters.add(new EnergyDrink(world, new TextureAtlas(\"EnergyDrink/Main.atlas\"),%f, %f, 30, 50, \\\"drink\\\"));\n",Herecules.getPosition().x * 200f, Herecules.getPosition().y * 200f);


        }

    }

    public void prints(String s) {


    }

}
