package com.lobseek.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lobseek.game.gui.Button;
import com.lobseek.game.gui.Container;
import com.lobseek.game.gui.Touch;
import com.lobseek.game.gui.TouchHandlerExample;

/**
 * Created by Whizzpered on 04.08.2016.
 */
public class Menu extends Stage {

    TouchHandlerExample tmp;
    public OrthographicCamera camera;

    public Menu(Viewport vp) {
        initViewport(vp);
        initGUI();
    }

    public void initViewport(Viewport vp) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, vp.getWorldWidth(), vp.getWorldHeight());
        camera.update();
        setViewport(vp);
        vp.setCamera(camera);
    }

    public void initGUI() {
        tmp = new TouchHandlerExample();
        tmp.create();
        Container gui = tmp.gui;
        gui.show();
        gui.setX(getWidth() / 2);
        gui.setY(getHeight() / 2);
        gui.setWidth(getWidth());
        gui.setHeight(getHeight());
        gui.add(new Button("upgrade", getWidth() / 2, getHeight() / 2 + 80, 200, 80) {
            @Override
            public void touchDown(Touch touch) {
                MyGdxGame.MYGDXGAME.setCurrent(MyGdxGame.MYGDXGAME.game);
            }

            @Override
            public void touchDragged(Touch touch) {

            }

            @Override
            public void touchUp(Touch touch) {

            }

            @Override
            public void touchHover(Touch touch) {

            }
        });
        gui.add(new Button("destroy", getWidth() / 2, getHeight() / 2 - 80, 200, 80) {
            @Override
            public void touchDown(Touch touch) {
                System.exit(0);
            }

            @Override
            public void touchDragged(Touch touch) {

            }

            @Override
            public void touchUp(Touch touch) {

            }

            @Override
            public void touchHover(Touch touch) {

            }
        });
        addListener(tmp);
        Gdx.input.setInputProcessor(this);
    }

    public void act(float delta) {
        super.act(delta);
        tmp.gui.act(delta);
    }

    @Override
    public void draw() {
        Batch b = getBatch();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b.setProjectionMatrix(camera.combined);
        camera.update();
        b.begin();
        tmp.gui.render(b, Gdx.graphics.getDeltaTime());
        b.end();
    }

}
