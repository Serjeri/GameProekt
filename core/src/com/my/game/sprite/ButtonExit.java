package com.my.game.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.my.game.Base.Button;
import com.my.game.math.Rect;


public class ButtonExit extends Button {

    private static final float padding = 0.05f;
    private static final float size = 0.2f;

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(size);
        setRight(worldBounds.getRight() - padding);
        setBottom(worldBounds.getBottom() + padding);
    }
}
