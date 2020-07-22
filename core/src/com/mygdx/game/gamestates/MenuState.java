package com.mygdx.game.gamestates;

import java.util.ArrayList;

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
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.*;
import Characters.MainCharacter;

public class MenuState extends GameState {

	//BackGround
	public static Texture backgroundTexture;
	public static Sprite backgroundSprite;

	//Button
	public static Texture T_button_1;
	public static Sprite S_button_1;
	
	private SpriteBatch sb;
	private ShapeRenderer sr;
	
	//private BitmapFont titleFont;
	private BitmapFont font;
	
	//private final String title = "Hercules";
	
	private int currentItem;
	private String[] menuItems;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sb = new SpriteBatch();
		sr = new ShapeRenderer();

		//BackGround
		backgroundTexture = new Texture("Background_menu.png");
		backgroundSprite =new Sprite(backgroundTexture);
		backgroundSprite.setSize(1280,960);

		//Button
		T_button_1 = new Texture("B_1.jpg");
		S_button_1 =new Sprite(T_button_1);
		S_button_1.setSize(300,50);
		
		//Title
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
			Gdx.files.internal("Hyperspace Bold.ttf")
		);
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //params.size = 56;
        //params.color = Color.WHITE;
		//titleFont = gen.generateFont(params);
		
		//Button
		FreeTypeFontGenerator.FreeTypeFontParameter params1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params1.size = 30;
        params1.color = Color.WHITE;
		font = gen.generateFont(params1);
		
		menuItems = new String[] {
			"Play",
			"Highscores",
			"Quit"
		};
		
		Save.load();
	}
	
	public void update(float dt) {
		
		handleInput();
	}
	
	public void draw(float dt) {
		
		sb.setProjectionMatrix(MyGdxGame.cam.combined);
		sr.setProjectionMatrix(MyGdxGame.cam.combined);
		
		sb.begin();

		//BackGround
		backgroundSprite.draw(sb);
		
		// draw title
		GlyphLayout layout = new GlyphLayout();
		//layout.setText(titleFont, title);
		//float width = layout.width;
		
		//titleFont.draw(
		//	sb,
		//	title,
		//	(MyGdxGame.WIDTH - width) / 2,
		//	300
		//);
		
		// draw menu
		for(int i = 0; i < menuItems.length; i++) {
			GlyphLayout layout_1 = new GlyphLayout();
			//layout_1.setText(titleFont, menuItems[i]);
			float width = layout.width;
			if(currentItem == i) font.setColor(Color.RED);
			else font.setColor(Color.WHITE);
			//font.setColor(Color.WHITE);
			S_button_1.setPosition(150f,500 - 80 * i);
			S_button_1.draw(sb);
			if(i==0)
				font.draw(
					sb,
					menuItems[i],
					270f,
					532
				);
			else if(i==1)
				font.draw(
						sb,
						menuItems[i],
						226f,
						456
				);
			else
				font.draw(
						sb,
						menuItems[i],
						270f,
						379
				);
		}
		
		sb.end();
		
	}
	
	public void handleInput() {
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if(currentItem > 0) {
				currentItem--;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			if(currentItem < menuItems.length - 1) {
				currentItem++;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			select();
		}
		
	}
	
	private void select() {
		// play
		if(currentItem == 0) {
			MainCharacter.money=0;
			gsm.setState(GameStateManager.PLAY);
		}
		// high scores
		if(currentItem == 1) {
			gsm.setState(GameStateManager.HIGHSCORE);
		}
		// Exit
		else if(currentItem == 2) {
			Gdx.app.exit();
		}
	}
	
	public void dispose() {
		sb.dispose();
		sr.dispose();
		//titleFont.dispose();
		font.dispose();
	}

}










