package com.prototype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.prototype.game.Screens.*;

public class TenkStars extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH=600;
	public static final int V_HEIGHT=500;
	public static final float PPM=2.5f;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		//setScreen(new Splash(this));
		//setScreen(new TankSelect(this));
		//setScreen(new GameOver(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
