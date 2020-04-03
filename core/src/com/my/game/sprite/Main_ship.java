package com.my.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.Ship;
import com.my.game.math.Rect;
import com.my.game.pool.BulletPool;
import com.my.game.pool.ExplosionPool;

public class Main_ship extends Ship {

    private static final int  invalind = -1;

    private boolean press_left;
    private boolean press_right;
    protected int left_pointer = invalind;
    protected int right_pointer = invalind;

    public Main_ship(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(atlas.findRegion("main_ship"),1,2,2);
        this.v = new Vector2();
        this.v0 = new Vector2(0.5f,0);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletV = new Vector2(0,0.5f);
        this.bulletPos =  new Vector2();
        this.bulletHeight = 0.01f;
        this.damage = 1;
        this.interval = 0.5f;
        this.hp = 10;
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
    }
    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.15f);
        setBottom(worldBounds.getBottom() + 0.03f );
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getTop());
        super.update(delta);
        if(getRight() > worldBounds.getRight()){
            setRight(worldBounds.getRight());
            stop();
        }if(getLeft() < worldBounds.getLeft()){
            setLeft(worldBounds.getLeft());
            stop();
        }
    }
    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (left_pointer != invalind) {
                return;
            }
            left_pointer = pointer;
            left();
        } else {
            if (right_pointer != invalind) {
                return;
            }
            right_pointer = pointer;
            right();
        }
    }

    @Override
    public void touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == left_pointer) {
            left_pointer = invalind;
            if (right_pointer != invalind) {
                right();
            } else {
                stop();
            }
        } else if (pointer == right_pointer) {
            right_pointer = invalind;
            if (left_pointer != invalind) {
                left();
            } else {
                stop();
            }
        }
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
            case Input.Keys.UP:
                shoot();
            case Input.Keys.W:
                shoot();
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
    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft() || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }

    private void right(){
        v.set(v0);
    }

    private void left(){
        v.set(v0).rotate(180);
    }

    private void stop(){
        v.setZero();
    }
}