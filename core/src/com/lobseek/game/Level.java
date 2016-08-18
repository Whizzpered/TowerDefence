package com.lobseek.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.lobseek.game.Tiles.OrigTile;
import com.lobseek.game.entities.Enemy;
import com.lobseek.game.entities.enemies.EliteSlime;
import com.lobseek.game.entities.enemies.Slime;
import com.lobseek.game.gui.Point;


/**
 * Created by Whizzpered on 7/22/2016.
 */
public class Level {

    public Array<Array<Point>> way = new Array<Array<Point>>();
    public Array<Array<Enemy>> wave = new Array<Array<Enemy>>();
    public float index;
    public int lives = 4, startgold = 300, waves;

    public Level(int index) {
        FileHandle fh = Gdx.files.internal("levels/" + index + ".level");
        String s = fh.readString("UTF-8");
        DataNotation tmp = DataNotation.fromString(s);
        //Here is begin of Way notation.
        for (int i = 0; i < tmp.get("ways").size(); i++) {
            // Reading way information from notation and adding way to the level's array of ways
            DataNotation w = tmp.get("ways").get("way" + i);
            way.add(new Array<Point>());
            // cycle for adding points of turns;
            for (int j = 0; j < w.size(); j++) {
                int x = w.get("point" + j).get(0).toInteger();
                int y = w.get("point" + j).get(1).toInteger();
                way.get(i).add(new Point(x, y));
            }
        }
        //Here is begin of Waves notation
        waves = tmp.get("waves").size();
        for (int i = 0; i < waves; i++) {
            // Reading wave information from notation and adding wave to the level's array of waves
            DataNotation w = tmp.get("waves").get("wave" + i);
            wave.add(new Array<Enemy>());
            // cycle for adding enemies;
            int way = w.get("way").toInteger(0);
            for (int j = 1; j < w.size(); j++) {
                setEnemy(w.get(j).getName(), w.get(j).toInteger(), i, way);
            }
            this.wave.get(i).reverse();
        }
    }

    public void setEnemy(String name, int count, int wave, int way) {
        for (int i = 0; i < count; i++) {
            Enemy en = null;
            if (name.equals("slime")) en = new Slime();
            if (name.equals("eliteSlime")) en = new EliteSlime();
            en.setWay(way);
            this.wave.get(wave).add(en);
        }
    }

    public void generateMap(Game game) {
        for (int o = 0; o < way.size; o++) {
            Array<Point> a = way.get(o);
            for (int i = 0; i < a.size - 1; i++) {
                Point s = a.get(i);
                Point e = a.get(i + 1);
                for (int x = (int) s.getX(); x < (int) e.getX(); x++) {
                    game.tiles[(int) s.getY()][x] = new OrigTile(1, false);
                }
                for (int y = (int) s.getY(); y > (int) e.getY(); y--) {
                    game.tiles[y][(int) s.getX()] = new OrigTile(1, false);
                }
                for (int y = (int) s.getY(); y < (int) e.getY(); y++) {
                    game.tiles[y][(int) s.getX()] = new OrigTile(1, false);
                }
                for (int x = (int) s.getX(); x > (int) e.getX(); x--) {
                    game.tiles[(int) s.getY()][x] = new OrigTile(1, false);
                }
            }
        }
        for (int y = 0; y < game.tiles.length; y++) {
            for (int x = 0; x < game.tiles[y].length; x++) {
                if (game.tiles[y][x] == null)
                    game.tiles[y][x] = new OrigTile(0, true);
            }
        }
    }

    public Point getWayPoint(int way, int order) {
        if (way < this.way.size & order < this.way.get(way).size) {
            return this.way.get(way).get(order);
        } else {
            return null;
        }
    }
}
