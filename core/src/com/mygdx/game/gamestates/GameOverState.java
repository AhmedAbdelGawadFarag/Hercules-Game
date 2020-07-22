package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.*;

public class GameOverState extends GameState {

	//BackGround
	public static Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	private SpriteBatch sb;
	private ShapeRenderer sr;
	
	private boolean newHighScore;
	private char[] newName;
	private int currentChar;
	
	//private BitmapFont gameOverFont;
	private BitmapFont font;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {

		//BackGround
		backgroundTexture = new Texture("gameover.png");
		backgroundSprite =new Sprite(backgroundTexture);
		backgroundSprite.setSize(1280,960);

		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		
		newHighScore = Save.gd.isHighScore(Save.gd.getTentativeScore());
		if(newHighScore) {
			newName = new char[] {'A', 'A', 'A', 'A', 'A'};
			currentChar = 0;
		}
		
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
			Gdx.files.internal("Hyperspace Bold.ttf")
		);
		FreeTypeFontGenerator.FreeTypeFontParameter params1 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params1.size = 40;
        params1.color = Color.GREEN;
		//gameOverFont = gen.generateFont(params1);
		font = gen.generateFont(params1);
		
	}
	
	public void update(float dt) {
		handleInput();
	}
	
	public void draw(float dt) {
		
		sb.setProjectionMatrix(MyGdxGame.cam.combined);
		
		sb.begin();

		//BackGround
		backgroundSprite.draw(sb);

		String s;
		float w;
		
		//s = "Game Over";
		//GlyphLayout layout = new GlyphLayout();
		//layout.setText(gameOverFont, s);
		//w = layout.width;
		//gameOverFont.draw(sb, s, (MyGdxGame.WIDTH - w) / 2, 700);
		
		if(!newHighScore) {
			sb.end();
			return;
		}
		
		s = "New High Score: " + Save.gd.getTentativeScore();
		GlyphLayout layout_1 = new GlyphLayout();
		layout_1.setText(font, s);
		w = layout_1.width;
		font.draw(sb, s, 400, 350);
		
		for(int i = 0; i < newName.length; i++) {
			font.draw(
				sb,
				Character.toString(newName[i]),
				538 + 37 * i,
				280
			);
		}
		
		sb.end();
		
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.rect(
				538 + 37 * currentChar,
				245,
				21,
				1
		);
		/*
		sr.line(
				538 + 37 * currentChar,
			245,
				548 + 37 * currentChar,
			245
		);*/
		sr.end();
		
	}
	
	public void handleInput() {
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			if(newHighScore) {
				Save.gd.addHighScore(
					Save.gd.getTentativeScore(),
					new String(newName)
				);
				Save.save();
			}
			gsm.setState(GameStateManager.MENU);
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if(newName[currentChar] == ' ') {
				newName[currentChar] = 'Z';
			}
			else {
				newName[currentChar]--;
				if(newName[currentChar] < 'A') {
					newName[currentChar] = ' ';
				}
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			if(newName[currentChar] == ' ') {
				newName[currentChar] = 'A';
			}
			else {
				newName[currentChar]++;
				if(newName[currentChar] > 'Z') {
					newName[currentChar] = ' ';
				}
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			if(currentChar < newName.length - 1) {
				currentChar++;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			if(currentChar > 0) {
				currentChar--;
			}
		}
		
	}
	
	public void dispose() {
		sb.dispose();
		sr.dispose();
		//gameOverFont.dispose();
		font.dispose();
	}
	
}









