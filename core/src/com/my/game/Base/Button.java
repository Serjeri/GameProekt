package com.my.game.Base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Button extends  Sprite {
    private static final float press = 0.9f;

    private int pointer;
    private boolean pressed;

    public Button(TextureRegion region) {
        super(region);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        if(pressed || !isMe(touch)){
            return;
        }
        this.pointer = pointer;
        this.scale = press;
        this.pressed = true;

    }

    @Override
    public void touchUp(Vector2 touch, int pointer, int button) {
        if(this.pointer != pointer || !pressed){
            return;
        }if(isMe(touch)){
            action();
        }
        this.pressed = false;
        this.scale = 1f;
    }

    public abstract void action();
}
