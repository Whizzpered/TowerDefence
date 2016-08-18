package com.lobseek.game;

/**
 * Created by Whizzpered on 7/22/2016.
 */
public abstract class Timer {

    public float current, time;
    public boolean loop, launghed = false;
    public int times = 0, cur = 0;

    public boolean isRunning() {
        return current > 0;
    }

    public Timer(float time, boolean loop) {
        this.time = time;
        this.loop = loop;
    }

    public Timer(float time, boolean loop, int times) {
        this.time = time;
        this.loop = loop;
        this.times = times;
    }

    public void start() {
        current = time;
        launghed = true;
        if (cur > 0) {
            cur = 0;
        }
    }

    public void act(float delta) {
        if (launghed) {
            if (times == 0 | cur < times) {
                if (current > 0) {
                    current -= delta;
                }
                if (current <= 0) {
                    action();
                    if (loop) current = time;
                }
            }
        }
    }

    public abstract void action();

}
