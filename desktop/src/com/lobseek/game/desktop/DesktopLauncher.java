package com.lobseek.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lobseek.game.MyGdxGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "I've seen some shit";
        config.width = 480;
        config.height = 800;
        new LwjglApplication(new MyGdxGame(), config);
    }
}
