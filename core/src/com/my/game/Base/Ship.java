package com.my.game.Base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.math.Rect;
import com.my.game.pool.BulletPool;
import com.my.game.pool.ExplosionPool;
import com.my.game.sprite.Bullet;
import com.my.game.sprite.Explosion;

public class Ship extends Sprite {

    protected  Vector2 v;
    protected  Vector2 v0;

    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected  Vector2 bulletV;
    protected  Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;

    protected final float damageInterval = 0.5f;
    protected  float damageTimer = damageInterval;

    protected int hp;

    protected float time;
    protected float interval;

    protected Sound shootSound;
    protected ExplosionPool explosionPool;


    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        time += delta;

        if(time >= interval){
            time = 0;
            shoot();
        }
        damageTimer += delta;
        if(damageTimer >= damageInterval){
            frame = 0;
        }
    }
    public void dispose() {
        shootSound.dispose();
    }

    @Override
    public void destroy() {
        super.destroy();
        this.hp = 0;
        Boom();
    }
    public void Damage( int damage){
        this.hp -= damage;
        if(hp <= 0){
            destroy();
        }
        damageTimer= 0f;
        frame = 1;
    }

    protected void shoot() {
        shootSound.play();
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV,bulletHeight, worldBounds, damage);
    }
    protected void Boom (){
        Explosion explosi = explosionPool.obtain();
        explosi.set(getHeight(),pos);

    }
}
