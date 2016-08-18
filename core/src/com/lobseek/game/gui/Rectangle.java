package com.lobseek.game.gui;

import java.io.Serializable;

/**
 * Прямоугольник
 *
 * @author phdengelhardt
 */
public class Rectangle extends Point implements Serializable, Cloneable {

    /**
     * Индекс класса Rectangle для сериализации
     */
    private static final long serialVersionUID = -2057001712460314007L;

    /**
     * Ширина прямоугольника
     */
    private double width,
    /**
     * Высота прямоугольника
     */
    height;

    /**
     * Конструктор по умолчанию
     */
    public Rectangle() {
        //Пусто
    }

    /**
     * Клонирующий конструктор
     *
     * @param r оригинальный прямоугольник
     */
    public Rectangle(Rectangle r) {
        setX(r.getX());
        setY(r.getY());
        this.width = r.width;
        this.height = r.height;
    }

    /**
     * Клонирующий конструктор из точки
     *
     * @param p оригинальная точка
     */
    public Rectangle(Point p) {
        setX(p.getX());
        setY(p.getY());
    }

    /**
     * Геттер ширины
     *
     * @return ширина
     */
    public double getWidth() {
        return width;
    }

    /**
     * Сеттер ширины
     *
     * @param width новая ширина
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Геттер высоты
     *
     * @return высота
     */
    public double getHeight() {
        return height;
    }

    /**
     * Сеттер высоты
     *
     * @param height новая высота
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Клонирование прямоугольника
     *
     * @return клон прямоугольника
     * @throws java.lang.CloneNotSupportedException
     * @see java.lang.Cloneable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Rectangle(this);
    }
}
