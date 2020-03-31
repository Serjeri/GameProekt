package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;
import com.my.game.math.Rnd;

public class Star extends Sprite {

    private final Vector2 v;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2();
        v.set(Rnd.nextFloat(-0.005f,0.005f), Rnd.nextFloat(-0.1f,-0.01f));

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.01f);
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX , posY);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if(getRight() < worldBounds.getLeft()){
            setLeft(worldBounds.getRight());
        }if(getLeft() > worldBounds.getRight()){
            setLeft(worldBounds.getLeft());
        }if(getTop() < worldBounds.getBottom()){
            setBottom(worldBounds.getTop());
        }if(getBottom() > worldBounds.getTop()){
            setTop(worldBounds.getBottom());
        }
    }
}
