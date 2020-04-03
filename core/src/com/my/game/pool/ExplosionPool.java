package com.my.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.my.game.Base.SpritePool;
import com.my.game.sprite.Explosion;

public class ExplosionPool extends SpritePool<Explosion> {

    private TextureAtlas atlas;
    private Sound exploseSound;

    public ExplosionPool(TextureAtlas atlas, Sound exploseSound) {
        this.atlas = atlas;
        this.exploseSound = exploseSound;
    }
    @Override
    protected Explosion newObject() {
        return new Explosion(atlas,exploseSound);
    }
}
