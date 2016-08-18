package com.lobseek.game.gui;

/**
 * Интерфейс кликабельности
 *
 * @author phdengelhardt
 */
public abstract interface Clickable {

    /**
     * Проверка события нажатия на виджет
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    public abstract Clickable isTouchDown(Touch touch);

    /**
     * Проверка события свайпа виджета
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    public abstract Clickable isTouchDragged(Touch touch);

    /**
     * Проверка события поднятия пальца с виджета
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    public abstract Clickable isTouchUp(Touch touch);

    /**
     * Проверка события движения курсора над виджетом
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    public abstract Clickable isTouchHover(Touch touch);

    /**
     * Обработка события нажатия на виджет
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    public abstract void touchDown(Touch touch);

    /**
     * Обработка события свайпа виджета
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    public abstract void touchDragged(Touch touch);

    /**
     * Обработка события поднятия пальца с виджета
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    public abstract void touchUp(Touch touch);

    /**
     * Обработка события движения курсора над виджетом
     *
     * @param touch данные о прикосновении
     */
    @EventHandler
    public abstract void touchHover(Touch touch);
}
