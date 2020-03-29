package com.my.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.my.game.Base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f,1,0,0.3f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img,  200, 135 );
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }
}
