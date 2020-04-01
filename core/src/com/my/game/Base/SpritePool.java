package com.my.game.Base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritePool <T extends Sprite> {

    private final List<T> activ = new ArrayList<>();
    private final List<T> Free = new ArrayList<>();

    protected abstract T newObject();

    public T obtain(){
        T object;
        if(Free.isEmpty()){
            object = newObject();

        }else {
            object = Free.remove(Free.size() - 1);
        }
        activ.add(object);
        return object;
    }

    public  void updateSprite(float delta){
        for (Sprite sprite:activ) {
            if(!sprite.destroy()){
                sprite.update(delta);
            }
        }
    }
    public  void drawSprite(SpriteBatch batch){
        for (Sprite sprite:activ) {
            if(!sprite.destroy()){
                sprite.draw(batch);
            }
        }
    }
}
