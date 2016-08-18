package com.lobseek.game.entities.enemies;

import com.lobseek.game.entities.Enemy;

/**
 * Created by Whizzpered on 27.07.2016.
 */
public class Slime extends Enemy {

    public Slime(float x, float y, int way) {
        super(x, y, way, "slime");
    }

    public Slime() {
        super("slime");
    }
}
