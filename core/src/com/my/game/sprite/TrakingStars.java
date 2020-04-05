package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class TrakingStars extends Star {

    private final Vector2 trakingV;
    private final Vector2 sumV = new Vector2();

    public TrakingStars(TextureAtlas atlas,Vector2 trakingV) {
        super(atlas);
        this.trakingV = trakingV;
    }

    @Override
    public void update(float delta) {
        sumV.setZero().mulAdd(trakingV,0.2f).rotate(180).add(v);
        pos.mulAdd(sumV,delta);
        chekAndHandleBounds();
    }
}
