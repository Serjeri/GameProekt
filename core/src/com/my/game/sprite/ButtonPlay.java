package com.my.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.my.game.Base.Button;
import com.my.game.Screen.GameScreen;
import com.my.game.math.Rect;

public class ButtonPlay extends Button {

    private final Game game;

    private static final float padding = 0.05f;

    public ButtonPlay(TextureAtlas atlas,Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.26f);
        setLeft(worldBounds.getLeft() + padding);
        setBottom(worldBounds.getBottom() + padding);
    }
}
