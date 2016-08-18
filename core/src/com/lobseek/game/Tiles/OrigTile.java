package com.lobseek.game.Tiles;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Whizzpered on 7/24/2016.
 */
public class OrigTile extends Tile {

    public OrigTile(int id, boolean buildable) {
        super(id, buildable);
    }

    @Override
    public void draw(Batch b, float x, float y) {
        sprite.setPosition(x, y);
        sprite.draw(b);
    }
}
