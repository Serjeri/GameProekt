package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;

public class Game_Over extends Sprite {
    public Game_Over(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.06f);
        setBottom(0.05f);
    }
}
