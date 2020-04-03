package com.my.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.my.game.Base.SpritePool;
import com.my.game.math.Rect;
import com.my.game.sprite.Enemy;

public class EnemyPool extends SpritePool<Enemy> {

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Sound shootSound;
    private Rect worldBounds;

    public EnemyPool(BulletPool bulletPool,ExplosionPool explosionPool ,Sound soundShoot,Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.shootSound = soundShoot;
        this.worldBounds = worldBounds;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool,explosionPool,shootSound,worldBounds);
    }

}
