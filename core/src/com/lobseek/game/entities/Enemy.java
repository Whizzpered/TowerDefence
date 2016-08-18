package com.lobseek.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.lobseek.game.entities.Effects.Regeneration;
import com.lobseek.game.gui.Point;

/**
 * Created by Whizzpered on 7/22/2016.
 */
public class Enemy extends Entity {

    int order = 0;
    public int way = 0;
    Point target;
    float s = 0, speed;
    int armor = 0;
    public String name;

    public Enemy(float x, float y, int way, String name) {
        super(x, y);
        maxhp = 200;
        this.name = name;
        this.way = way;
        initEffects();
    }

    public void setWay(int w) {
        way = w;
    }

    public void initEffects() {
        addEffect(new Regeneration(this, 20));
    }

    public Enemy(String name) {
        this.name = name;
    }

    @Override
    public void setSprite() {
        if (name == null) {
            model = new Model("slime", 1.5f);
        } else {
            model = new Model(name, 1.5f);
        }
        if (way >= getStage().level.way.size) {
            way = 0;
        }
        initHp();
        setTarget();
    }

    public void initHp() {
        maxhp += getStage().currentwave * 2;
        price = getStage().currentwave;
        hp = maxhp;
    }

    public void setTarget() {
        target = getStage().level.getWayPoint(way, order);
        if (target != null) {
            target = new Point(target);
            target.x *= 64;
            target.y *= 64;
        }
    }

    public void move(float speed, float angle) {
        super.move(speed, angle);
        s += speed;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if ((int) hp <= 0) {
            die();
        }

        if (getStage() != null) {
            float dist = (float) Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2));
            if (dist <= 5) {
                order++;
                setTarget();
                if (target == null) {
                    alive();
                }
            } else {
                float angle = (float) Math.atan2(target.getY() - getY(), target.getX() - getX());
                move(1f, angle);
            }
        }
    }

    public void alive() {
        getStage().level.lives--;
        getStage().remove(this);
    }

    public void die() {
        getStage().gold++;
        getStage().remove(this);
    }

    @Override
    public void draw(Batch b, float parentalpha) {
        float x = getX(), y = getY() / 2;
        float r = 1, g = 1, bl = 1, al = 1;
        for (int i = 0; i < effects.size; i++) {
            Color c = effects.get(i).color;
            r *= c.r;
            g *= c.g;
            bl *= c.b;
            al *= c.a;
        }
        b.setColor(new Color(r, g, bl, al));
        if (model != null) {
            Animation a = model.getAnimation();
            b.draw(a.getKeyFrame(adelta), x, y);
            if (hp > 0) {
                getStage().font.setColor(Color.RED);
                getStage().font.draw(b, (int) hp + "", x + getWidth() / 2, y + getHeight() + 18);
            }
        }
        b.setColor(Color.WHITE);
    }

}
