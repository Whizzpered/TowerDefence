package com.lobseek.game.Tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lobseek.game.MyGdxGame;

/**
 * Created by Whizzpered on 7/23/2016.
 */
public abstract class Tile {

    public int index, size = 64;
    public boolean buildable, free = true;
    public Sprite sprite;

    public Tile(int id, boolean buildable) {
        index = id;
        this.buildable = buildable;
        sprite = MyGdxGame.MYGDXGAME.game.getAtlas().createSprite("terrain/" + id);
    }

    public abstract void draw(Batch b, float x, float y);

}
