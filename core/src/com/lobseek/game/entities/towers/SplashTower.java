package com.lobseek.game.entities.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lobseek.game.entities.Shell;
import com.lobseek.game.entities.Tower;
import com.lobseek.game.entities.shells.SplashShell;

/**
 * Created by Whizzpered on 07.08.2016.
 */
public class SplashTower extends Tower {
    public SplashTower(float x, float y) {
        super(x, y);
        attackspeed = 0.6f;
        price = 6;
        splash = 80;
        splashDamage = 0.5f;
    }

    public void shoot(float delta) {
        delay = (1 / attackspeed);
        if (target != null) getStage().addEntity(new SplashShell(this, target));
    }

    @Override
    public void draw(Batch b, float parentalpha) {
        if (getStage() != null) {
            float x = getX(), y = getY() / 2;
            ShapeRenderer sp = getStage().sr;
            if (tapped) {
                b.end();
                sp.begin(ShapeRenderer.ShapeType.Line);
                sp.setProjectionMatrix(getStage().camera.combined);
                radius *= getStage().zoom;
                sp.ellipse(x - radius + getWidth() / 2, y - radius / 2 + getHeight() / 4, radius * 2, radius);
                radius /= getStage().zoom;
                sp.end();
                b.begin();
            }
            b.setColor(Color.RED);
            if (model != null) {
                Animation an = model.getAnimation();
                b.draw(an.getKeyFrame(adelta), (int) x, (int) y);
            }
            b.setColor(Color.WHITE);
        }
    }
}
