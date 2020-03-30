package com.my.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;

public class MyClass extends Sprite {

    private static final float V_len = 0.01f;
    private Vector2 touch;
    private Vector2 V;
    private Vector2 Buf;

    public MyClass(Texture region) {
        super(new TextureRegion(region));

        touch = new Vector2();
        V = new Vector2();
        Buf = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.4f);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        V.set(touch.sub(pos)).setLength(V_len);
    }

    @Override
    public void update(float delta) {

        Buf.set(touch);

        if (Buf.sub(pos).len() > V_len){
            pos.add(V);
        }else{
            pos.set(touch);
        }
    }
}
