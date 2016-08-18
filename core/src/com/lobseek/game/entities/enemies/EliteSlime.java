package com.lobseek.game.entities.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.lobseek.game.entities.Model;

/**
 * Created by Whizzpered on 04.08.2016.
 */
public class EliteSlime extends Slime {

    public EliteSlime(float x, float y, int way) {
        super(x, y, way);
    }

    public EliteSlime() {
        super();
    }

    @Override
    public void initHp() {
        maxhp += (int) (getStage().currentwave * 2.5f);
        price = (int) (getStage().currentwave * 1.5f);
        hp = maxhp;
    }

    @Override
    public void draw(Batch b, float parentalpha) {
        float x = getX(), y = getY() / 2;

        float r = 1, g = 0.1f, bl = 0.1f, al = 1;
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
