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
import com.my.game.sprite.Bullet;
import com.my.game.sprite.Button_New_Game;
import com.my.game.sprite.Enemy;
import com.my.game.sprite.Game_Over;
import com.my.game.sprite.Main_ship;
import com.my.game.sprite.Star;
import com.my.game.pool.BulletPool;
import com.my.game.utils.EnemiesEmitter;
import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int start_count = 128;
    private enum State {play, pause, game_over}

    private TextureAtlas atlas;
    private Texture bg;

    private Background background;
    private Star[] star;

    private Main_ship main_ship;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;

    private Music music;
    private Sound bulletSound;
    private Sound exploseSound;

    private EnemiesEmitter enemiesEmitter;

    private State state;
    private State prevState;

    private Game_Over game_over;
    private Button_New_Game button_new_game;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        exploseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        star = new Star[start_count];
        for (int i = 0; i < start_count; i++) {
            star[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, exploseSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, bulletSound, worldBounds);
        enemiesEmitter = new EnemiesEmitter(atlas, enemyPool, worldBounds);
        main_ship = new Main_ship(atlas, bulletPool, explosionPool);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        game_over = new Game_Over(atlas);
        button_new_game = new Button_New_Game(atlas, this);
        state = State.play;
    }

    @Override
    public void render(float delta) {
        update(delta);
        checColision();
        freeAllDestroed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star stars : star) {
            stars.resize(worldBounds);
        }
        main_ship.resize(worldBounds);
        game_over.resize(worldBounds);
        button_new_game.resize(worldBounds);
    }

    @Override
    public void pause() {
        music.pause();
        prevState = state;
        state = State.pause;
    }

    @Override
    public void resume() {
        music.play();
        state = prevState;
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispolse();
        enemyPool.dispolse();
        exploseSound.dispose();
        music.dispose();
        bulletSound.dispose();
        explosionPool.dispolse();
        main_ship.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.play) {
            main_ship.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.play) {
            main_ship.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.play) {
            main_ship.touchDown(touch, pointer, button);
        }else if(state == State.game_over){
            button_new_game.touchDown(touch,pointer,button);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.play) {
            main_ship.touchUp(touch, pointer, button);
        }else if(state == State.game_over){
            button_new_game.touchUp(touch,pointer,button);
        }
        return false;
    }

    private void update(float delta) {
        for (Star stars : star) {
            stars.update(delta);
        }
        explosionPool.updateActiveSprites(delta);
        if (state == State.play) {
            main_ship.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            enemiesEmitter.generate(delta);
        }
    }
    private void checColision() {
        if (state != State.play) {
            return;
        }
// не разобрался Copy Past с видео урока.
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            float minDist = main_ship.getHalfWidth() + enemy.getHalfWidth();
            if (main_ship.pos.dst(enemy.pos) <= minDist) {
                enemy.destroy();
                main_ship.Damage(enemy.getDamage());
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() != main_ship) {
                if (main_ship.isBulletCollision(bullet)) {
                    main_ship.Damage(bullet.getDamage());
                    bullet.destroy();
                }
                continue;
            }
            for (Enemy enemy : enemyList) {
                if (enemy.isBulletCollision(bullet)) {
                    enemy.Damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
        if (main_ship.isDestroyed()) {
            state = State.game_over;
        }
    }
    private void freeAllDestroed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star stars : star) {
            stars.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        if (state == State.play) {
            main_ship.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
        } else if (state == State.game_over) {
            game_over.draw(batch);
            button_new_game.draw(batch);
        }
        batch.end();
    }

    public void startNewGame () {
        // не разобрался copy past  с видео
        state = State.play;
        main_ship.startGame();
        bulletPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
    }
}