package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;
import com.my.game.math.Rnd;

public class Star extends Sprite {

    private static  final float start_heigh = 0.005f;

    private final Vector2 v;
    private Rect worldBounds;

    private float anime_time;
    private float anime_interval = 1f;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2();
        v.set(Rnd.nextFloat(-0.005f,0.005f), Rnd.nextFloat(-0.1f,-0.01f));
        anime_time = Rnd.nextFloat(0,1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(start_heigh);
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
        anime_time += delta;

        if(anime_time >= anime_interval){
            anime_time = 0;
            setHeightProportion(start_heigh);
        }else {
            setHeightProportion(getHeight() + 0.0001f);
        }
    }

}
