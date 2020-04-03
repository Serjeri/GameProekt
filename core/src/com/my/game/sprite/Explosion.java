package com.my.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;

public class Explosion extends Sprite {

    private float interval = 0.017f;
    private float tamer;

    private Sound exploseSound;

    public Explosion(TextureAtlas atlas, Sound exploseSound) {
        super(atlas.findRegion("explosion"),9,9,74);
        this.exploseSound = exploseSound;
    }
    public void set(float height, Vector2 pos){
        this.pos.set(pos);
        setHeightProportion(height);
        exploseSound.play();
    }

    @Override
    public void update(float delta) {
        tamer += delta;
        if(tamer >= interval){
            tamer = 0;
            if(++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}
