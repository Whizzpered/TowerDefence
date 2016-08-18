package com.lobseek.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Виджет
 *
 * @author phdengelhardt
 */
public class Widget extends Rectangle {

    /**
     * Родитель виджета
     */
    private Widget parent;
    /**
     * Видимость виджета
     */
    private boolean visible;

    /**
     * Геттер родителя
     *
     * @return родитель
     */
    public Widget getParent() {
        return parent;
    }

    /**
     * Сеттер родителя
     *
     * @param parent новый родитель
     */
    public void setParent(Widget parent) {
        this.parent = parent;
    }

    /**
     * Метод отрисовки виджета
     *
     * @param batch полотно отрисовки
     * @param delta время в секундах между двумя отрисовками
     */
    public void render(Batch batch, double delta) {
    }

    /**
     * Метод действия виджета
     *
     * @param delta время в секундах между двумя действиями
     */
    public void act(double delta) {
    }

    /**
     * Геттер координаты X
     *
     * @return координата Х
     */
    @Override
    public float getX() {
        if (parent == null) {
            return super.getX();
        } else {
            return parent.getX() + super.getX();
        }
    }

    /**
     * Геттер координаты Y
     *
     * @return координата Y
     */
    @Override
    public float getY() {
        if (parent == null) {
            return super.getY();
        } else {
            return parent.getY() + super.getY();
        }
    }

    /**
     * Проверка пересечения с точкой
     *
     * @param координата X точки
     * @param координата Y точки
     */
    public boolean isInBounds(double x, double y) {
        return (
                Math.abs(x - getX()) <= getWidth() / 2 &&
                        Math.abs(y - getY()) <= getHeight() / 2
        );
    }

    /**
     * Геттер видимости виджета
     *
     * @return видимость
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Сеттер видимости виджета
     *
     * @param новую видимость
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Скрыть виджет
     */
    public void hide() {
        this.visible = false;
    }

    /**
     * Показать виджет
     */
    public void show() {
        this.visible = true;
    }
}
