package com.my.game.pool;

import com.my.game.Base.SpritePool;
import com.my.game.sprite.Bullet;

public class BulletPool extends SpritePool <Bullet> {


    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
