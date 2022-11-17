package com.prototype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.prototype.game.Screens.PlayScreen;

public class TenkStars extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH=400;
	public static final int V_HEIGHT=208;
	public static final float PPM=1;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		//V_HEIGHT= Gdx.graphics.getHeight();
		//V_WIDTH=Gdx.graphics.getWidth();
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
