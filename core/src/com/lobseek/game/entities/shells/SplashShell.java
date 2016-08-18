package com.lobseek.game.entities.shells;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.lobseek.game.MyGdxGame;
import com.lobseek.game.entities.Enemy;
import com.lobseek.game.entities.Entity;
import com.lobseek.game.entities.Shell;
import com.lobseek.game.entities.Tower;

/**
 * Created by Whizzpered on 07.08.2016.
 */
public class SplashShell extends Shell {
    public SplashShell(Entity owner, Entity target) {
        super(owner, target);
    }

    @Override
    public void setSprite() {
        sprite = getStage().getAtlas().createSprite("arrow");
        TextureAtlas ta = MyGdxGame.MYGDXGAME.game.getAtlas();
        death = new Animation(0.1f, ta.findRegions("ice_explosion"));
        deathTimer = 0;
    }

    @Override
    public void hit() {
        float dist;
        for (Enemy en : getStage().enemies) {
            dist = (float) Math.sqrt(Math.pow(en.getX() - getX(), 2) + Math.pow(en.getY() - getY(), 2));
            if (dist < ((Tower) owner).splash) {
                en.hp -= owner.damage * ((Tower) owner).splashDamage;
            }
        }
        done = true;
    }

    @Override
    public void act(float delta) {

        if (done) {
            deathTimer += delta;
            if (deathTimer >= death.getAnimationDuration()) {
                getStage().remove(this);
            }
        } else {
            moving();
        }
    }

    public void draw(Batch b, float parentalpha) {
        if (!done) {
            super.draw(b, parentalpha);
        } else {
            float sp = ((Tower) owner).splash;
            b.setColor(Color.WHITE);
            b.draw(death.getKeyFrame(deathTimer), getX() - sp / 2, getY() / 2 - sp / 2, sp, sp / 2);
        }
    }
}
