package com.my.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private static final float V_len = 1f;
    private Texture img;
    private Vector2 touch;
    private Vector2 V;
    private Vector2 positions;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        touch = new Vector2();
        V = new Vector2();
        positions = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f,1,0,0.3f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        if((positions.y + img.getHeight()) < Gdx.graphics.getHeight()){
//        }
        if (touch.cpy().sub(positions).len() > V_len )
            positions.add(V);
        else
            positions.set(touch);
        batch.begin();
        batch.draw(img,  positions.x,positions.y);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        V.set(touch.cpy().sub(positions).setLength(V_len));
        //  System.out.println("touch.X = " + touch.X + " touchY = " +  touch.Y);
        return false;
    }
}
//2.Реализовать движение логотипа badlogic (можно свою картинку вставить)
//        при нажатии клавиши мыши (touchDown) в точку нажатия на экране и остановку в данной точке.
