package com.lobseek.game.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.lobseek.game.MyGdxGame;

/**
 * Примерный класс, скрестишь его со своими стейджами там
 *
 * @author phdengelhardt
 */
public class TouchHandlerExample extends InputListener {

    /**
     * Число возможных прикосновений
     */
    final int numberOfTouches = 10;
    /**
     * Список возможных прикосновений
     */
    final Touch[] touches = new Touch[numberOfTouches];

    /**
     * Контейнер гуя
     */
    public Container gui;

    /**
     * Метод создания этой поебени. Массив надо заполнить.
     *
     * @return true если создание завершилось без ошибок, иначе false.
     */
    public boolean create() {
        gui = new Container();
        for (int i = 0; i < touches.length; i++) {
            touches[i] = new Touch();
        }
        return true;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int we) {
        if (pointer < touches.length) {
            Touch t = touches[pointer];
            t.setLx(t.getX());
            t.setLy(t.getY());
            t.setX(x);
            t.setY(y);
            t.setDx(t.getX() - t.getLx());
            t.setDy(t.getY() - t.getLy());
            t.setDown(true);
            Clickable ow = gui.isTouchDown(t);
            if (ow != null) {
                ow.touchDown(t);
                if (ow instanceof Dragable) {
                    t.setObjectedWidget(ow);
                }
                return true;
            }
        }
        return touchDownWorld(x, y);
    }

    public boolean touchDownWorld(float x, float y) {
        return false;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int we) {
        if (pointer < touches.length) {
            Touch t = touches[pointer];
            t.setLx(t.getX());
            t.setLy(t.getY());
            t.setX(x);
            t.setY(y);
            t.setDx(t.getX() - t.getLx());
            t.setDy(t.getY() - t.getLy());
            t.setDown(false);
            if (t.getObjectedWidget() != null) {
                t.getObjectedWidget().touchUp(t);
                t.setObjectedWidget(null);
                return;
            }

            Clickable ow = gui.isTouchUp(t);
            if (ow != null) {
                ow.touchUp(t);
                return;
            }
        }
        touchUpWorld(x, y);
    }

    public void touchUpWorld(float x, float y) {

    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (pointer < touches.length) {
            Touch t = touches[pointer];
            t.setLx(t.getX());
            t.setLy(t.getY());
            t.setX(x);
            t.setY(y);
            t.setDx(t.getX() - t.getLx());
            t.setDy(t.getY() - t.getLy());
            t.setDown(true);
            if (t.getObjectedWidget() != null) {
                t.getObjectedWidget().touchDragged(t);
                return;
            }

            Clickable ow = gui.isTouchDragged(t);
            if (ow != null) {
                ow.touchDragged(t);
                if (ow instanceof Dragable) {
                    t.setObjectedWidget(ow);
                }
                return;
            }
        }
        touchDraggedWorld(x, y);
    }

    public void touchDraggedWorld(float x, float y) {

    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        Touch t = touches[0];
        t.setLx(t.getX());
        t.setLy(t.getY());
        t.setX(x);
        t.setY(y);
        t.setDx(t.getX() - t.getLx());
        t.setDy(t.getY() - t.getLy());
        t.setDown(false);

        Clickable ow = gui.isTouchHover(t);
        if (ow != null) {
            ow.touchHover(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        return false;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        return false;
    }
}
