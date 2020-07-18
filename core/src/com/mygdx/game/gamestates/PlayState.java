package com.mygdx.game.gamestates;
import com.mygdx.game.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class PlayState extends GameState {
	
	private SpriteBatch batch, b_batch;
	private TextureAtlas atlas_coin;
	private Sprite Coin,Coin_1; //update to ArrayList
	private TextureRegion region_coin;
	private Rectangle RectangleCoin, RectangleCoin_1; //update to ArrayList
	private Rectangle RectangleChar;
	int currframe;
	float stateTime;
	
	private TextureAtlas atlas;
	private Sprite Characters;
	private TextureRegion region;
	int currframe_char;
	float x_postion=0;
	float x_position=0;
	
	private int score;
	private String yourScoreName;
	private BitmapFont yourBitmapFontName;
	
	//update to ArrayList
	boolean b=true,b1=true;
	boolean b_1=true,b1_1=true;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		atlas_coin = new TextureAtlas(Gdx.files.internal("coins.atlas"));
		region_coin = atlas_coin.findRegion("0");
		Coin = new Sprite(region_coin);
		Coin_1 = new Sprite(region_coin);
		currframe=1;
		batch = new SpriteBatch();
		b_batch = new SpriteBatch();
		stateTime=0f;
		
		atlas = new TextureAtlas(Gdx.files.internal("MainCharacter.atlas"));
		region = atlas.findRegion("001");
		Characters = new Sprite(region);
		currframe_char=1;
		Coin.setPosition(650f, 0f);
		Coin_1.setPosition(550f, 0f);
		
		RectangleCoin = new Rectangle(Coin.getX(), Coin.getY(), 
			Coin.getWidth(), Coin.getHeight());
		RectangleCoin_1 = new Rectangle(Coin_1.getX(), Coin_1.getY(), 
				Coin_1.getWidth(), Coin_1.getHeight());
		RectangleChar =new Rectangle(0.0f, 0.4f, 0.8f, 0.083f);
		
		score = 0;
	    yourScoreName = "score: 0";
	    yourBitmapFontName = new BitmapFont();
	    yourBitmapFontName.setColor(Color.RED);
	    yourBitmapFontName.getData().setScale(2f, 2f);
		
	}

	
	public void update(float dt) {
		handleInput();
		
	}
	
	
	public void draw() {
		
		batch.setProjectionMatrix(MyGdxGame.cam.combined);
		batch.setProjectionMatrix(MyGdxGame.cam.combined);
		
		boolean isoverlapping = RectangleChar.overlaps(RectangleCoin);
		boolean isoverlapping_1 = RectangleChar.overlaps(RectangleCoin_1);
		
		RectangleChar.setPosition(x_position, 0.4f);
		Gdx.gl20.glClearColor(1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += 0.25f;
		if(stateTime>=1) {
			currframe++;
			stateTime=0f;
		}
        if (currframe > 5)
        	currframe = 0;
        
        String inttostring = Integer.toString(currframe);
        String index_char = inttostring;
        
        Coin.setRegion(atlas_coin.findRegion(index_char));
        Coin_1.setRegion(atlas_coin.findRegion(index_char));
        
        yourScoreName = "score: " + score;
        b_batch.begin();
        yourBitmapFontName.setColor(Color.BLACK);
		yourBitmapFontName.draw(b_batch, yourScoreName, 10, 400);
		b_batch.end();
		
		batch.begin();
		
		
		//Coin.setPosition(650, 0);
		if(! isoverlapping&&b) {
			Coin.draw(batch);
		}
		else {
			b=false;
			if(b1) {
				score+=20;
				b1=false;
				Save.gd.setTenativeScore(score);
				gsm.setState(GameStateManager.GAMEOVER);
				return;
			}
		}
		if(! isoverlapping_1&&b_1) {
			Coin_1.draw(batch);
		}
		else {
			b_1=false;
			if(b1_1) {
				score+=20;
				b1_1=false;
			}
		}
		
		Characters.draw(batch);
		
		batch.end();
		
	}
	
	public void handleInput() {
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			currframe_char++;

	        if (currframe_char > 7) 
	        	currframe_char = 1;
	        
	        String inttostring = Integer.toString(currframe_char);
	        String index_char = "00"+inttostring;
	        x_postion+=10;
	        x_position+=10;
	        
	        Characters.setPosition(x_postion, 0);
	        Characters.setRegion(atlas.findRegion(index_char));
		}
	}
	
	public void dispose() {
		batch.dispose();
	}
}
