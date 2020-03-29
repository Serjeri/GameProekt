package com.my.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture imgFon;
	TextureRegion region;
	TextureRegion regionFon;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		imgFon = new Texture("fon.jpg");
		region = new TextureRegion(img, 20,25,150,100);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,0.3f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img,  200, 135 );
		batch.setColor(0,0,0,0.9f);
		batch.draw(imgFon,  0, 0 ,700,700);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
