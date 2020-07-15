package Screen;

import Characters.GameCharacter;
import Characters.MainCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hercules.HerculesGame;

public class GamePlayScreen extends ScreenAdapter {
    HerculesGame game;
    GameCharacter MainCharacter;
    SpriteBatch batch;

    public GamePlayScreen(HerculesGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.MainCharacter = new MainCharacter(new TextureAtlas(Gdx.files.internal("MainCharacter.atlas")));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        MainCharacter.Move();
        MainCharacter.attack();
        MainCharacter.draw(batch);

        batch.end();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
