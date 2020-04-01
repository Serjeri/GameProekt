package com.my.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Sprite;
import com.my.game.math.Rect;


public class Main_ship extends Sprite {

    private static final float size_ship = 0.1f;
    private static final float padding = 0.05f;

    private boolean press_left;
    private boolean press_right;

    private Vector2 V1;
    private Vector2 V2;

    private Rect worldBounds;

    public Main_ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        V1 = new Vector2();
        V2 = new Vector2(0.5f,0);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(size_ship);
        setBottom(worldBounds.getBottom() + padding );
        this.worldBounds = worldBounds;
    }


    public void keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                press_left = true;
                left();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                press_right = true;
                right();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                press_left = false;
                if(press_right){
                    right();
                }else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                press_right = false;
                if(press_left){
                    left();
                }else {
                    stop();
                }
                break;
        }
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(V1,delta);
    }

    private void right(){
        V1.set(V2);
    }

    private void left(){
        V1.set(V2).rotate(180);
    }

    private void stop(){
        V1.setZero();
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x ){
            left();
        }else {
            right();
        }
    }

    @Override
    public void touchUp(Vector2 touch, int pointer, int button) {
        stop();
    }
}
