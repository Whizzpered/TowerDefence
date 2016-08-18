package com.lobseek.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lobseek.game.MyGdxGame;

/**
 * Кнопка
 *
 * @author phdengelhardt
 */
public abstract class Button extends Widget implements Clickable {

    /**
     * Перечисление состояний спрайта
     *
     * @author phdengelhardt
     */
    private enum SpriteState {
        NOT_LOADED,
        LOADED,
        ERROR
    }

    /**
     * Название спрайта
     */
    private String sprite;
    /**
     * Объект спрайта LibGDX
     */
    private Sprite spriteLibgdx;
    /**
     * Текущее состояние спрайта
     */
    private SpriteState spriteState = SpriteState.ERROR;

    /**
     * Стандартный конструктор
     */
    public Button() {

    }

    /**
     * Конструктор
     *
     * @param sprite имя спрайта
     */
    public Button(String sprite) {
        setSprite(sprite);
    }

    /**
     * Конструктор
     *
     * @param x координата центра кнопки на оси X
     * @param y координата центра кнопки на оси Y
     */
    public Button(float x, float y) {
        setX(x);
        setY(y);
        setWidth(-1);
    }

    /**
     * Конструктор
     *
     * @param sprite имя спрайта
     * @param x      координата центра кнопки на оси X
     * @param y      координата центра кнопки на оси Y
     * @param width  ширина кнопки
     * @param height высота кнопки
     */
    public Button(String sprite, float x, float y, double width, double height) {
        setSprite(sprite);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Конструктор
     *
     * @param sprite имя спрайта
     * @param x      координата центра кнопки на оси X
     * @param y      координата центра кнопки на оси Y
     */
    public Button(String sprite, float x, float y) {
        setSprite(sprite);
        setX(x);
        setY(y);
        setWidth(-1);
    }

    /**
     * Конструктор
     *
     * @param x      координата центра кнопки на оси X
     * @param y      координата центра кнопки на оси Y
     * @param width  ширина кнопки
     * @param height высота кнопки
     */
    public Button(float x, float y, double width, double height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Проверка события нажатия на кнопку
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchDown(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY()))
            return this;
        else
            return null;
    }

    /**
     * Проверка события свайпа кнопки
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchDragged(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY()))
            return this;
        else
            return null;
    }

    /**
     * Проверка события поднятия пальца с кнопки
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchUp(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY()))
            return this;
        else
            return null;
    }

    /**
     * Проверка события движения курсора над кнопкой
     *
     * @param touch данные о прикосновении
     * @return этот виджет, если прикосновение произошло
     */
    @Override
    public Clickable isTouchHover(Touch touch) {
        if (isVisible() && isInBounds(touch.getX(), touch.getY()))
            return this;
        else
            return null;
    }

    /**
     * Метод отрисовки кнопки
     *
     * @param batch полотно отрисовки
     * @param delta время в секундах между двумя отрисовками
     */
    @Override
    public void render(Batch batch, double delta) {
        if (spriteState == SpriteState.ERROR)
            return;

        if (spriteState == SpriteState.NOT_LOADED) {
            if (sprite != null && sprite.length() > 0) {
                spriteLibgdx = MyGdxGame.MYGDXGAME
                        .game.getAtlas().createSprite("gui/" + sprite);
                if (getWidth() < 0) {
                    setWidth(spriteLibgdx.getWidth());
                    setHeight(spriteLibgdx.getHeight());
                }
                if (spriteLibgdx != null) {
                    spriteState = SpriteState.LOADED;
                } else {
                    spriteState = SpriteState.ERROR;
                    return;
                }
            }
        }

        spriteLibgdx.setSize((int) getWidth(), (int) getHeight());
        spriteLibgdx.setCenter((int) getX(), (int) getY());
        spriteLibgdx.draw(batch);
    }

    public void move(float speed, float angle) {
        setX(getX() + speed * (float) Math.cos(angle));
        setY(getY() + speed * (float) Math.sin(angle));
    }

    /**
     * Метод действия кнопки
     *
     * @param delta время в секундах между двумя действиями
     */
    @Override
    public void act(double delta) {
    }

    /**
     * Геттер имени спрайта
     *
     * @return имя спрайта
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * Сеттер имени спрайта
     * новое имя спрайта
     */
    public void setSprite(String sprite) {
        if (sprite != null) {
            if (!sprite.equals(this.sprite)) {
                this.sprite = sprite;
                this.spriteState = SpriteState.NOT_LOADED;
            }
        } else {
            this.sprite = null;
            this.spriteState = SpriteState.ERROR;
        }
    }
}
