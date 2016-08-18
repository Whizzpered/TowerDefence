package com.lobseek.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Whizzpered on 7/22/2016.
 */
public class Tower extends Entity {

    public float radius = 240, attackspeed = 1f, splashDamage, splash;
    protected Enemy target;
    Enemy priority;
    public int level;
    protected float delay;
    public boolean tapped;

    public Tower(float x, float y) {
        super(x * 64, y * 64);
        damage = 1;
        price = 3;
    }

    @Override
    public void act(float delta) {
        if (target == null) {
            findingEnemy();
        } else {
            angle = (float) Math.atan2(target.getY() - getY(), target.getX() - getX());
            move(0, angle);
            if (delay <= model.getAnimation().getAnimationDuration()) {
                super.act(delta);
            }
            if (delay <= 0) {
                shoot(delta);
                adelta = 0;
            } else {
                delay -= delta;
            }
            float dist = (float) Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2));
            if ((int) target.hp <= 0 | dist > radius) {
                target = null;
            }
        }

    }

    public void findingEnemy() {
        float s = 0;
        for (Enemy en : getStage().enemies) {
            float dist = (float) Math.sqrt(Math.pow(en.getX() - getX(), 2) + Math.pow(en.getY() - getY(), 2));
            if (dist <= radius && en.s > s) {
                s = en.s;
                target = en;
            }
        }
    }

    public void shoot(float delta) {
        delay = (1 / attackspeed);
        if (target != null) getStage().addEntity(new Shell(this, target));
    }

    @Override
    public void draw(Batch b, float parentalpha) {
        if (getStage() != null) {
            float x = getX(), y = getY() / 2;
            ShapeRenderer sp = getStage().sr;
            if (tapped) {
                b.end();
                sp.begin(ShapeRenderer.ShapeType.Line);
                sp.setProjectionMatrix(getStage().camera.combined);
                radius *= getStage().zoom;
                sp.ellipse(x - radius + getWidth() / 2, y - radius / 2 + getHeight() / 4, radius * 2, radius);
                radius /= getStage().zoom;
                sp.end();
                b.begin();
            }
            b.setColor(Color.WHITE);
            if (model != null) {
                Animation an = model.getAnimation();
                b.draw(an.getKeyFrame(adelta), (int) x, (int) y);
            }
        }
    }
}