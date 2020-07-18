package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.*;
import com.badlogic.gdx.graphics.Color;

public class HighScoreState extends GameState {
	
	private SpriteBatch sb;
	
	private BitmapFont font;
	
	private long[] highScores;
	private String[] names;
	
	public HighScoreState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
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
		
		sb.setProjectionMatrix(MyGdxGame.cam.combined);
		
		sb.begin();
		
		String s;
		float w;
		
		s = "High Scores";
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, s);
		w = layout.width;
		font.draw(sb, s, (MyGdxGame.WIDTH - w) / 2, 300);
		
		for(int i = 0; i < highScores.length; i++) {
			String inttostring = Integer.toString(i+1);
			s = String.format(
					"%2s. %7s %s",
					inttostring,
					highScores[i],
					names[i]
			);
			w = layout.width;
			font.draw(sb, s, (MyGdxGame.WIDTH - w) / 2, 270 - 20 * i);
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








