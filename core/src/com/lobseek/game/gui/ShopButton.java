package com.lobseek.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.lobseek.game.Game;
import com.lobseek.game.MyGdxGame;
import com.lobseek.game.entities.Tower;

/**
 * Created by Whizzpered on 07.08.2016.
 */
public class ShopButton extends Button {

    public Tower target, upgrade;
    public float angle, r;

    public ShopButton() {
        setTower();
    }

    public ShopButton(String sprite, float x, float y, float width, float height) {
        super(sprite, x, y, width, height);
        setTower();
    }

    public void setTower() {

    }

    @Override
    public void act(double delta) {
        super.act(delta);
        if (target != null) {
            Point p = MyGdxGame.MYGDXGAME.game.toWorldCoors(getX(), getY());
            double dist = Math.sqrt(Math.pow(target.getX() - p.getX() + 32, 2) + Math.pow(target.getY() / 2 - p.getY() + 32, 2));
            if (dist < r) {
                move(7f, angle);
            }
        }
    }

    @Override
    public void touchDown(Touch touch) {

    }

    @Override
    public void touchDragged(Touch touch) {

    }

    @Override
    public void touchUp(Touch touch) {
        Game game = MyGdxGame.MYGDXGAME.game;
        if (upgrade != null) {
            if (game.gold >= upgrade.price) {
                upgrade.setPosition(target.getX(), target.getY());
                game.towers.removeValue(target, true);
                target.remove();
                game.addEntity(upgrade);
                upgrade.setSprite();
                game.gold -= upgrade.price;
                upgrade.price += target.price;
                for (ShopButton s : game.shop)
                    s.hide();
            }
        } else {
            if (getSprite().equals("destroy")) {
                int x = (int) target.getX() / 64;
                int y = (int) target.getY() / 64;
                game.tiles[y][x].free = true;
                game.towers.removeValue(target, true);
                target.remove();
                game.gold += (int) (target.price * 0.7f);
                hide();
                for (ShopButton s : game.shop)
                    s.hide();
            }
        }
    }

    @Override
    public void touchHover(Touch touch) {

    }

    @Override
    public void render(Batch b, double delta) {
        if (upgrade != null && MyGdxGame.MYGDXGAME.game.gold < upgrade.price) {
            b.setColor(Color.GRAY);
        }
        super.render(b, delta);
        b.setColor(Color.WHITE);
    }
}
