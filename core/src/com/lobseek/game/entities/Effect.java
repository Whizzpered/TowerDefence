package com.lobseek.game.entities;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Whizzpered on 28.07.2016.
 */
public class Effect {

    public Entity target;
    public Color color = new Color(1f, 1f, 1f, 1f);
    public float duration, time, reload, count;
    public int level;

    public Effect(Entity target, float duration, float reload) {
        this.target = target;
        this.duration = duration;
        this.reload = reload;
        time = duration;
    }

    public Effect(Entity target, float duration, float reload, Color color) {
        this.target = target;
        this.duration = duration;
        this.color = color;
        this.reload = reload;
        time = duration;
    }

    public void act(float delta) {
        if (time > 0) {
            time -= delta;
            if (count > 0) {
                count -= delta;
            } else {
                apply();
                count = reload;
            }
        } else {
            target.effects.removeValue(this, true);
        }
    }

    public void apply() {
    }
}
