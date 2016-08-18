package com.lobseek.game.gui;

/**
 * Обработчик событий прикосновений
 *
 * @author phdengelhardt
 */
public class Touch {

    /**
     * Выбранный виджет
     */
    private Clickable objectedWidget = null;
    /**
     * Нажатость
     */
    private boolean down;
    /**
     * Координата Х
     */
    private float x;
    /**
     * Координата Y
     */
    private float y;
    /**
     * Изменение координаты Х с момента предыдущего события тапа
     */
    private float dx;
    /**
     * Изменение координаты Y с момента предыдущего события тапа
     */
    private float dy;
    /**
     * Предыдущая координата X
     */
    private float lx;
    /**
     * Предыдущая координата Y
     */
    private float ly;

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the dx
     */
    public float getDx() {
        return dx;
    }

    /**
     * @param dx the dx to set
     */
    public void setDx(float dx) {
        this.dx = dx;
    }

    /**
     * @return the dy
     */
    public float getDy() {
        return dy;
    }

    /**
     * @param dy the dy to set
     */
    public void setDy(float dy) {
        this.dy = dy;
    }

    /**
     * @return the lx
     */
    public float getLx() {
        return lx;
    }

    /**
     * @param lx the lx to set
     */
    public void setLx(float lx) {
        this.lx = lx;
    }

    /**
     * @return the ly
     */
    public float getLy() {
        return ly;
    }

    /**
     * @param ly the ly to set
     */
    public void setLy(float ly) {
        this.ly = ly;
    }

    /**
     * @return the objectedWidget
     */
    public Clickable getObjectedWidget() {
        return objectedWidget;
    }

    /**
     * @param objectedWidget the objectedWidget to set
     */
    public void setObjectedWidget(Clickable objectedWidget) {
        this.objectedWidget = objectedWidget;
    }
}
