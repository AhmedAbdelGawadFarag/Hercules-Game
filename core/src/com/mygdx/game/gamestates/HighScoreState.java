package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.*;
import com.badlogic.gdx.graphics.Color;

public class HighScoreState extends GameState {

	//BackGround
	public static Texture backgroundTexture;
	public static Sprite backgroundSprite;
	public static Texture backgroundTexture_HighScore;
	public static Sprite backgroundSprite_HighScore;

	public static Texture[] backgroundTexture_icon;
	public static Sprite[] backgroundSprite_icon;
	
	private SpriteBatch sb;
	
	private BitmapFont font;
	
	private long[] highScores;
	private String[] names;
	private int count,count_1;

	public HighScoreState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {

		count=count_1=0;
		//BackGround
		backgroundTexture = new Texture("background_highscore.jpg");
		backgroundSprite =new Sprite(backgroundTexture);
		backgroundSprite.setSize(1280,960);

		backgroundTexture_HighScore = new Texture("high-score.png");
		backgroundSprite_HighScore =new Sprite(backgroundTexture_HighScore);
		backgroundSprite_HighScore.setPosition(440, 500);
		backgroundSprite_HighScore.setSize(400,220);

		backgroundTexture_icon=new Texture[10];
		backgroundSprite_icon=new Sprite[10];
		for(int i=0;i<10;i++) {
			backgroundTexture_icon[i] = new Texture("number_icon/number-"+Integer.toString(i+1)+"-icon.png");
			backgroundSprite_icon[i] = new Sprite(backgroundTexture_icon[i]);
			if(i<5)
				backgroundSprite_icon[i].setPosition(400, 450-(i*50));
			else {

				backgroundSprite_icon[i].setPosition(750, 450 - (count * 50));
				count++;
			}
		}
		
		sb = new SpriteBatch();
		
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
			Gdx.files.internal("Hyperspace Bold.ttf")
		);
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 20;
        params.color = Color.WHITE;
        font = gen.generateFont(params);
		
		Save.load();
		highScores = Save.gd.getHighScores();
		names = Save.gd.getNames();
		
	}
	
	public void update(float dt) {
		handleInput();
	}
	
	public void draw(float dt) {
		count_1=0;
		sb.setProjectionMatrix(MyGdxGame.cam.combined);
		
		sb.begin();

		//BackGround
		backgroundSprite.draw(sb);
		backgroundSprite_HighScore.draw(sb);
		for(int i=0;i<10;i++)
			backgroundSprite_icon[i].draw(sb);
		
		String s;
		float w;
		
		//s = "High Scores";
		GlyphLayout layout = new GlyphLayout();
		//layout.setText(font, s);
		//w = layout.width;
		//font.draw(sb, s, (MyGdxGame.WIDTH - w) / 2, 300);
		
		for(int i = 0; i < highScores.length; i++) {
			String inttostring = Integer.toString(i+1);
			s = String.format(
					"%7s %s",
					highScores[i],
					names[i]
			);
			w = layout.width;
			if(i<5)
				font.draw(sb, s, 470, 487 - (53 * i));
			else {
				font.draw(sb, s, 800, 487 - (53 * count_1));
				count_1++;
			}
		}
		
		sb.end();
		
	}
	
	public void handleInput() {
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ||
				Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) gsm.setState(GameStateManager.MENU);
	}
	
	public void dispose() {
		sb.dispose();
		font.dispose();
	}
	
}








