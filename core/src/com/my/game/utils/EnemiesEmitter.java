package com.my.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.my.game.math.Rect;
import com.my.game.math.Rnd;
import com.my.game.pool.EnemyPool;
import com.my.game.sprite.Enemy;

public class EnemiesEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.15f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.3f;
    private static final int ENEMY_MEDIUM_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 10;

    private int level = 1;

    private Rect worldBounds;

    private float generateInterval = 4f;
    private float generateTimer;

    private final TextureRegion[] enemySmallRegions;
    private final TextureRegion[] enemyMediumRegions;
    private final TextureRegion[] enemyBigRegions;

    private final Vector2 enemySmallV = new Vector2(0f, -0.1f*level);
    private final Vector2 enemyMediumV = new Vector2(0f, -0.02f*level);
    private final Vector2 enemyBigV = new Vector2(0f, -0.05f*level);
    private TextureRegion bulletRegion;

    private final EnemyPool enemyPool;

    public EnemiesEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.enemySmallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
        this.enemyMediumRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        this.enemyBigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generate(float delta, int frags) {
        generateTimer += delta;
        level = frags / 3 + 1;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemy.set(
                        enemySmallRegions,
                        enemySmallV ,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_DAMAGE * level,
                        ENEMY_SMALL_RELOAD_INTERVAL * level,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP * level
                );
            } else if (type < 0.8) {
                enemy.set(
                        enemyMediumRegions,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_DAMAGE * level,
                        ENEMY_MEDIUM_RELOAD_INTERVAL* level,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP * level
                );
            } else {
                enemy.set(
                        enemyBigRegions,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_DAMAGE * level,
                        ENEMY_BIG_RELOAD_INTERVAL* level,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP * level
                );
            }
            enemy.setBottom(worldBounds.getTop());
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
        }
    }

    public int getLevel() {
        return level;
    }
}

