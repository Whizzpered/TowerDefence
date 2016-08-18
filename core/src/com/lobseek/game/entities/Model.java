package com.lobseek.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.lobseek.game.MyGdxGame;

/**
 * Created by Whizzpered on 7/23/2016.
 */
public class Model {

    private Sprite left, up, down, right;
    private Animation move_left, move_up, move_down, move_right;
    private Sprite currentSprite;
    private Animation currentAnimation;

    public Model(String name) {
        if (name != null) {
            initSprites(name);
            initAnimations(name);
        }
    }

    public Model(String name, float scale) {
        if (name != null) {
            initSprites(name);
            initAnimations(name);
            setScale(scale);
        }
    }

    public void initSprites(String name) {
        TextureAtlas ta = MyGdxGame.MYGDXGAME.game.getAtlas();
        left = ta.createSprite(name + "/left", 0);
        up = ta.createSprite(name + "/up", 0);
        down = ta.createSprite(name + "/down", 0);
        right = ta.createSprite(name + "/right", 0);
        currentSprite = down;
    }

    public void initAnimations(String name) {
        TextureAtlas ta = MyGdxGame.MYGDXGAME.game.getAtlas();
        move_left = new Animation(0.1f, ta.findRegions(name + "/left"));
        move_left.setPlayMode(Animation.PlayMode.LOOP);
        move_up = new Animation(0.1f, ta.findRegions(name + "/up"));
        move_up.setPlayMode(Animation.PlayMode.LOOP);
        move_right = new Animation(0.1f, ta.findRegions(name + "/right"));
        move_right.setPlayMode(Animation.PlayMode.LOOP);
        move_down = new Animation(0.1f, ta.findRegions(name + "/down"));
        move_down.setPlayMode(Animation.PlayMode.LOOP);
        currentAnimation = move_down;
    }

    public Animation getAnimation() {
        return currentAnimation;
    }

    public void setScale(float scale) {
        if (left != null) left.setScale(scale);
        if (up != null) up.setScale(scale);
        if (down != null) down.setScale(scale);
        if (right != null) right.setScale(scale);
    }

    public void setSprite(Entity.Rotation r) {
        switch (r) {
            case LEFT:
                if (left != null) {
                    currentSprite = left;
                    currentAnimation = move_left;
                }
                break;
            case RIGHT:
                if (right != null) {
                    currentSprite = right;
                    currentAnimation = move_right;
                }
                break;
            case UP:
                if (up != null) {
                    currentSprite = up;
                    currentAnimation = move_up;
                }
                break;
            case DOWN:
                if (down != null) {
                    currentAnimation = move_down;
                }
                break;
        }
    }

    public Sprite getSprite() {
        return currentSprite;
    }

}
