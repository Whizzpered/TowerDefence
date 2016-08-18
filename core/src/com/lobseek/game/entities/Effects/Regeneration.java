package com.lobseek.game.entities.Effects;

import com.badlogic.gdx.graphics.Color;
import com.lobseek.game.entities.Effect;
import com.lobseek.game.entities.Entity;


/**
 * Created by Whizzpered on 02.08.2016.
 */
public class Regeneration extends Effect {

    public Regeneration(Entity to, float dur) {
        super(to, dur, 2f, Color.WHITE);
    }

    @Override
    public void apply() {
        if (target.hp < target.maxhp) {
            target.hp++;
        }
    }

}
