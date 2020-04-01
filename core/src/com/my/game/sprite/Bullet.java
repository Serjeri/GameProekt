package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;

public class Bullet extends Sprite {

    private Rect worldBounds;
    private final Vector2 v;
    private int damage;
    private Sprite owener;

    public Bullet() {
        regions = new TextureRegion[1];
        v = new Vector2();
    }


    public void set(Sprite owner,TextureRegion region,Vector2 v0 ,Vector2 pos0,float height, Rect worldBounds, int damage) {

        setHeightProportion(height);
        this.owener = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        this.worldBounds = worldBounds;
        this.damage = damage;

    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if(isOutside(worldBounds)){
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwener() {
        return owener;
    }
}
