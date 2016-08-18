package com.lobseek.game.entities;

/**
 * Created by Whizzpered on 01.08.2016.
 */
public abstract class Buff extends Effect {

    public Buff(Entity target, float duration) {
        super(target, duration, duration);
        apply();
    }

    @Override
    public void act(float delta) {
        if (time > 0) {
            time -= delta;
        } else {
            cease();
        }
    }

    public abstract void apply();

    public abstract void cease();
}
