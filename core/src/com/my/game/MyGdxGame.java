package com.my.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture img;

	//TextureRegion region;
	//TextureRegion regionFon;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		//imgFon = new Texture("fon.jpg");
		//region = new TextureRegion(img, 20,25,150,100);
		Vector2 v1 = new Vector2(1,1);
		Vector2 v2 = new Vector2(2,2);
		v1.add(v2);
		System.out.println("v1 add v2 v1.x = " + v1.x + "v1.y = " + v1.y);

		v1.set(3, 2);
		v2.set(5, 6);
		v2.sub(v1);
		System.out.println("v2 add v1 v2.x = " + v2.x + " v2.y = " + v2.y);
		System.out.println("v2.len = " + v2.len());

		v1.set(10, 0);
		System.out.println("v1.len() =  " + v1.len());
		v1.scl(0.9f);
		System.out.println("v1 len()= " + v1.len());
		System.out.println("v1.scl(0.9f); v1.x = " + v1.x + "v1.y = " + v1.y);

		v1.nor();
		System.out.println("v1.len() =  " + v1.len());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,0.3f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img,  200, 135 );
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
