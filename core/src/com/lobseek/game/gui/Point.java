package com.lobseek.game.gui;

import java.io.Serializable;

/**
 * Точка
 *
 * @author phdengelhardt
 */
public class Point implements Serializable, Cloneable {

    /**
     * Индекс класса Point для сериализации
     */
    private static final long serialVersionUID = 3585152576762684567L;

    /**
     * Координата на оси абсцисс
     */
    public float x,
    /**
     * Координата на оси ординат
     */
    y;

    /**
     * Конструктор по умолчанию
     */
    public Point() {
        //Пусто
    }

    /**
     * Клонирующий конструктор
     *
     * @param p оригинальная точка
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Конструктор с координатами
     *
     * @param x координата на оси абсцисс
     * @param y координата на оси ординат
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер Х
     *
     * @return значение координаты Х
     */
    public float getX() {
        return x;
    }


    /**
     * Сеттер Х
     *
     * @param value устанавливаемое значение координаты Х
     */
    public void setX(float value) {
        x = value;
    }

    /**
     * Геттер Y
     *
     * @return значение координаты Y
     */
    public float getY() {
        return y;
    }

    /**
     * Сеттер Y
     *
     * @param value устанавливаемое значение координаты Y
     */
    public void setY(float value) {
        y = value;
    }

    /**
     * Клонирование точки
     *
     * @return клон точки
     * @throws java.lang.CloneNotSupportedException
     * @see java.lang.Cloneable
     */

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Point(this);
    }

}
