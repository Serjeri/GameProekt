package com.my.game.Base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class New_Game extends  Sprite {
    private int Buttton;
    private  boolean pressed;

    public New_Game(TextureRegion region) {
        super(region);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        if(pressed || !isMe(touch)){
            return;
        }
        this.Buttton = pointer;
        this.scale = 0.1f;
        this.pressed = true;
    }

    @Override
    public void touchUp(Vector2 touch, int pointer, int button) {
      if(this.Buttton != pointer || !pressed){
          return;
      }if (isMe(touch)){
          action();
        }
      pressed = false;
      scale = 1f;
    }
    public abstract void action();
}
