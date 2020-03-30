package com.my.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.my.game.Base.BaseScreen;
import com.my.game.math.Rect;
import com.my.game.sprite.Background;
import com.my.game.sprite.MyClass;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;

    private MyClass myClass ;

    private Background background;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        myClass = new MyClass(img);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        myClass.update(delta);
        batch.begin();
        background.draw(batch);
        myClass.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        myClass.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        myClass.touchDown(touch,pointer,button);
        return false;
    }
}
