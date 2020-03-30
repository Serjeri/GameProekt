package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;

public class MyClass extends Sprite {

    private Vector2 touch;
    private Vector2 V;

    public MyClass(TextureRegion region) {
        super(region);
        touch = new Vector2();
        V = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
       setHeightProportion(0.5f);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        super.touchDown(touch, pointer, button);
        touch.set();
        touch.set((touch));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
