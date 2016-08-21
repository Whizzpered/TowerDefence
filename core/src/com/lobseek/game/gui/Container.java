package com.lobseek.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.lobseek.game.MyGdxGame;

/**
 * Контейнер виджетов
 *
 * @author phdengelhardt
 */
public class Container extends Widget implements Clickable {

    /**
     * Список виджетов в контейнере
     */
    Array<Widget> elements = new Array<Widget>();

    /**
     * Добавление виджета в контейнер
     *
     * @param widget добавляемый виджет
     */
    public void add(Widget widget) {
        elements.add(widget);
        widget.setVisible(this.isVisible());
    }

    /**
     * Удаление виджета из контейнера
     *
     * @param widget удаляемый виджет
     */
    public void remove(Widget widget) {
        elements.removeValue(widget, true);
    }

    /**
     * Метод отрисовки контейнера
     *
     * @param batch полотно отрисовки
     * @param delta время в секундах между двумя отрисовками
     */
    @Override
    public void render(Batch batch, double delta) {
        for (int i = 0; i < elements.size; i++) {
            Widget w = elements.get(i);
            if (w.isVisible())
                w.render(batch, delta);
        }
    }

    /**
     * Метод действия контейнера
     *
     * @param delta время в секундах между двумя действиями
     */
    @Override
    public void act(double delta) {
        for (int i = 0; i < elements.size; i++) {
            elements.get(i).act(delta);
        }
    }

    /**
     * Проверка события нажатия на виджет
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchDown(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY())) {
            for (int i = elements.size - 1; i >= 0; i--) {
                Widget e = elements.get(i);
                if (e instanceof Clickable) {
                    Clickable c = (Clickable) e;
                    c = c.isTouchDown(touch);
                    if (c != null)
                        return c;
                }
            }
            return this;
        } else
            return null;
    }

    public void tap(Touch touch){

    }

    /**
     * Проверка события свайпа виджета
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchDragged(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY())) {
            for (int i = elements.size - 1; i >= 0; i--) {
                Widget e = elements.get(i);
                if (e instanceof Clickable) {
                    Clickable c = (Clickable) e;
                    c = c.isTouchDragged(touch);
                    if (c != null)
                        return c;
                }
            }
            return this;
        } else
            return null;
    }

    /**
     * Проверка события поднятия пальца с виджета
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchUp(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY())) {
            for (int i = elements.size - 1; i >= 0; i--) {
                Widget e = elements.get(i);
                if (e instanceof Clickable) {
                    Clickable c = (Clickable) e;
                    c = c.isTouchUp(touch);
                    if (c != null)
                        return c;
                }
            }
            return this;
        } else
            return null;
    }

    /**
     * Проверка события движения курсора над виджетом
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchHover(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY())) {
            for (int i = elements.size - 1; i >= 0; i--) {
                Widget e = elements.get(i);
                if (e instanceof Clickable) {
                    Clickable c = (Clickable) e;
                    c = c.isTouchHover(touch);
                    if (c != null)
                        return c;
                }
            }
            return this;
        } else
            return null;
    }

    /**
     * Обработка события нажатия на виджет
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    @Override
    public void touchDown(Touch touch) {
    }

    /**
     * Обработка события свайпа виджета
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    @Override
    public void touchDragged(Touch touch) {

    }

    /**
     * Обработка события поднятия пальца с виджета
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    @Override
    public void touchUp(Touch touch) {

    }

    /**
     * Обработка события движения курсора над виджетом
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    @Override
    public void touchHover(Touch touch) {

    }

}
