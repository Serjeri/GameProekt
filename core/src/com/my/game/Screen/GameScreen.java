package com.my.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Base.BaseScreen;
import com.my.game.math.Rect;
import com.my.game.pool.EnemyPool;
import com.my.game.pool.ExplosionPool;
import com.my.game.sprite.Background;
import com.my.game.sprite.Main_ship;
import com.my.game.sprite.Star;

import com.my.game.pool.BulletPool;
import com.my.game.utils.EnemiesEmitter;

public class GameScreen extends BaseScreen {

    private static final int start_count = 128;
    private Star[] star;

    private TextureAtlas atlas;
    private Texture bg;

    private Background background;
    private Main_ship main_ship;

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Music music;

    private Sound bulletSound;

    private EnemyPool enemyPool;
    private EnemiesEmitter enemiesEmitter;
    private Sound exploseSound;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        exploseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        star = new Star[start_count];
        for (int i = 0; i < start_count ; i++) {
            star[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas,exploseSound);
        enemyPool = new EnemyPool(bulletPool,explosionPool, bulletSound,worldBounds);
        enemiesEmitter = new EnemiesEmitter(atlas, enemyPool,worldBounds);
        main_ship = new Main_ship(atlas, bulletPool,explosionPool);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));

        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta) {
        update(delta);
        freeAllDestroed();
        draw();
    }

    private void update(float delta){
        main_ship.update(delta);
        for (Star star : star) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
        enemiesEmitter.generate(delta);

    }

    private void draw(){
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        main_ship.draw(batch);
        for (Star star : star) {
            star.draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        main_ship.resize(worldBounds);
        for (Star star : star) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispolse();
        enemyPool.dispolse();
        music.dispose();
        bulletSound.dispose();
        exploseSound.dispose();
        main_ship.dispose();
        explosionPool.dispolse();

    }

    @Override
    public boolean keyDown(int keycode) {
        main_ship.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        main_ship.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        main_ship.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        main_ship.touchUp(touch, pointer, button);
        return false;
    }

    private void freeAllDestroed (){
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
    }
    private void checColision (){

    }
}