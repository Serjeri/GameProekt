package com.my.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Ship;
import com.my.game.math.Rect;
import com.my.game.pool.BulletPool;
import com.my.game.pool.ExplosionPool;

public class Enemy extends Ship {

    private final Vector2 emeniV;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Rect worldBounds) {

        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
        this.worldBounds = worldBounds;

        this.v = new Vector2();
        this.v0 = new Vector2(0,-0.1f);

        this.bulletV = new Vector2(0,0.9f);
        this.bulletPos =  new Vector2();
        this.emeniV = new Vector2(0,-0.5f);
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getBottom());
        super.update(delta);
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        } else {
            this.time = interval*0.9f;
        }
        if(getBottom() < worldBounds.getBottom()){
            destroy();
        }
    }
    public void set(TextureRegion[] regions,Vector2 v0,TextureRegion bulletRegion, float bulletHeight, float bulletVY,int damage,float reloadInterval,float height,int hp){
        this.regions = regions;
        this.v.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.interval = reloadInterval;
        this.time = 0f;
        setHeightProportion(height);
        this.hp = hp;
        v.set(emeniV);
    }
    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft() || bullet.getLeft() > getRight() || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }
}
