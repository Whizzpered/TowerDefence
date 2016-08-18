package com.lobseek.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.lobseek.game.Game;

/**
 * Created by Whizzpered on 7/21/2016.
 */
public class Entity extends Actor implements Comparable {

    protected float adelta = 0;

    @Override
    public int compareTo(Object o) {
        float delta = ((Entity) o).getY() - getY();
        if (delta != 0) delta /= Math.abs(delta);
        return (int) delta;
    }

    public float getWidth() {
        if (model != null) return model.getSprite().getWidth();
        else return sprite.getWidth();
    }

    public float getHeight() {
        if (model != null) return model.getSprite().getHeight();
        else return sprite.getHeight();
    }

    public enum Rotation {
        LEFT, RIGHT, UP, DOWN;
    }

    Rotation rot = Rotation.DOWN;
    protected Model model;
    protected Sprite sprite;
    public float angle;
    public float hp, maxhp, damage, level;
    public int price;

    public void addEffect(Effect ef) {
        for (int i = 0; i < effects.size; i++) {
            Effect e = effects.get(i);
            if (e.getClass() == ef.getClass()) {
                if (e.level < ef.level || e.time < 0.7f) {
                    effects.removeValue(e, true);
                    effects.add(ef);
                } else {
                    return;
                }
            }
        }
        effects.add(ef);
    }

    public Array<Effect> effects = new Array<Effect>();

    public Game getStage() {
        return (Game) super.getStage();
    }

    public Entity(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setSprite() {
        model = new Model("archer_tower", 2);
    }

    @Override
    public void act(float delta) {
        adelta += delta;
        for (int i = 0; i < effects.size; i++) {
            effects.get(i).act(delta);
        }
    }

    public void moveX(float speed) {
        setX(getX() + speed);
    }

    public void moveY(float speed) {
        setY(getY() + speed);
    }

    public void move(float speed, float angle) {
        int rotangle = (int) (angle / Math.PI * 180);
        rot = getRotation(rotangle);
        this.angle = rotangle;
        setX(getX() + speed * (float) Math.cos(angle));
        setY(getY() + speed * (float) Math.sin(angle));
        if (model != null) model.setSprite(getRotation(rotangle));
    }

    public Entity() {

    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    /*
     *  Here's getting Rotation function. angle is in grad's.
     */
    public Rotation getRotation(int angle) {
        if ((Math.abs(angle) > 135)) {
            return Rotation.LEFT;
        } else if (Math.abs(angle) < 45) {
            return Rotation.RIGHT;
        } else if (angle < 0) {
            return Rotation.DOWN;
        } else {
            return Rotation.UP;
        }
    }

    @Override
    public void draw(Batch b, float parentalpha) {
        float x = getX(), y = getY() / 2;
        b.setColor(Color.WHITE);
        if (model != null) {
            Sprite sp = model.getSprite();
            sp.setPosition(x, y);
            sp.draw(b);
            if (hp > 0) {
                getStage().font.setColor(Color.RED);
                getStage().font.draw(b, hp + "", x + getWidth() / 2, y + getHeight() + 18);
            }
        } else {
            sprite.setCenter(x, y);
            sprite.draw(b);
        }
    }
}
