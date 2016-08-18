package com.lobseek.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Whizzpered on 7/22/2016.
 */
public class Shell extends Entity {

    protected Entity target;
    protected Entity owner;
    protected float deathTimer;
    protected Animation death;
    protected boolean done = false;

    public Shell(Entity owner, Entity target) {
        super(owner.getX() + 32, owner.getY() + 64);
        this.owner = owner;
        this.target = target;
    }

    @Override
    public void setSprite() {
        sprite = getStage().getAtlas().createSprite("arrow");
    }

    @Override
    public void act(float delta) {
        moving();
    }

    public void moving() {
        if (target != null) {
            if (Math.sqrt(Math.pow(target.getX() + 32 - getX(), 2) + Math.pow(target.getY() + 64 - getY(), 2)) >= 10) {
                float angle = (float) Math.atan2(target.getY() + 64 - getY(), target.getX() + 32 - getX());
                move(8, angle);
            } else {
                hit();
            }
        }
    }

    public void hit() {
        if (target instanceof Enemy) {
            target.hp -= owner.damage;
            getStage().remove(this);
        } else {
            evilhit();
        }
    }

    public void evilhit() {

    }

    @Override
    public void draw(Batch b, float parentalpha) {
        sprite.setRotation(angle);
        sprite.setCenter(getX(), getY() / 2);
        sprite.draw(b);
    }
}
