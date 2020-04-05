package com.my.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.my.game.Base.New_Game;
import com.my.game.Screen.GameScreen;
import com.my.game.math.Rect;

public class Button_New_Game extends New_Game {

    private GameScreen gameScreen;
    public Button_New_Game(TextureAtlas atlas,GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.09f);
        setTop(-0.01f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
