package com.lobseek.game;

import com.badlogic.gdx.utils.Array;
import com.lobseek.game.entities.Enemy;

/**
 * Created by Whizzpered on 04.08.2016.
 */
public class SpawnTimer extends Timer {
    Array<Enemy> qeue;

    public SpawnTimer(Array<Enemy> in, float timing) {
        super(timing, true, in.size);
        qeue = in;
    }

    @Override
    public void action() {
        Game game = MyGdxGame.MYGDXGAME.game;
        if (qeue.size > 0) {
            Enemy en = qeue.pop();
            add(en, en.way);
        }
    }

    public void add(Enemy en, int way) {
        Game game = MyGdxGame.MYGDXGAME.game;
        if (way >= game.level.way.size) way = 0;
        float x = game.level.way.get(way).get(0).x * 64;
        float y = game.level.way.get(way).get(0).y * 64;
        en.setPosition(x, y);
        en.setWay(way);
        game.addEntity(en);
    }

    @Override
    public void start() {
        action();
        super.start();
    }

}
