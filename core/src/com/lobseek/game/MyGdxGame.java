package com.lobseek.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyGdxGame extends ApplicationAdapter {

    public static MyGdxGame MYGDXGAME;
    public Game game;
    public Menu menu;
    public Stage current;
    public static StretchViewport vp = new StretchViewport(480, 800);

    public void setCurrent(Stage s) {
        current = s;
        Gdx.input.setInputProcessor(current);
    }

    @Override
    public void create() {
        MYGDXGAME = this;
        game = new Game(vp);
        game.start();
        menu = new Menu(vp);
        current = menu;
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }

    @Override
    public void render() {
        current.act();
        current.draw();
    }

    @Override
    public void dispose() {
        current.dispose();
        super.dispose();
    }
}
