package com.mygdx.game.gamestates;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw(float dt);


	public abstract void handleInput();
	public abstract void dispose();
	
}
