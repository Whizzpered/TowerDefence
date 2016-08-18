package com.lobseek.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lobseek.game.Tiles.Tile;
import com.lobseek.game.entities.Enemy;
import com.lobseek.game.entities.Entity;
import com.lobseek.game.entities.Shell;
import com.lobseek.game.entities.Tower;
import com.lobseek.game.entities.towers.BasicTower;
import com.lobseek.game.entities.towers.SplashTower;
import com.lobseek.game.gui.Button;
import com.lobseek.game.gui.Container;
import com.lobseek.game.gui.Point;
import com.lobseek.game.gui.ShopButton;
import com.lobseek.game.gui.Touch;
import com.lobseek.game.gui.TouchHandlerExample;

/**
 * Created by Whizzpered on 7/21/2016.
 */
public class Game extends Stage {

    public static final int NUMBEROFTOWERS = 2;
    public AssetManager loader = new AssetManager();
    static TextureAtlas atlas;
    public int gold = 0, currentwave = 0;
    public float zoom = 0.75f;
    public OrthographicCamera camera;
    public OrthographicCamera guiCamera;
    public static BitmapFont font;
    public Level level;
    public Timer wave, spawn;
    public Tower hand;
    public Game game = this;
    public Tile[][] tiles = new Tile[64][32];
    public ShapeRenderer sr;
    boolean drag;
    TouchHandlerExample tmp;

    public Array<Shell> shells = new Array<Shell>();
    public Array<Tower> towers = new Array<Tower>();
    public Array<Enemy> enemies = new Array<Enemy>();
    public Array<Entity> entities = new Array<Entity>();
    public Array<ShopButton> shop = new Array<ShopButton>();

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Point toWorldCoors(float x, float y) {
        x *= zoom;
        y *= zoom;
        x += camera.position.x - getViewport().getWorldWidth() / 2 + 60;
        y += camera.position.y - getViewport().getWorldHeight() / 2 + 96;
        return new Point(x, y);
    }

    public Point toScreenCoors(float x, float y) {
        x -= (camera.position.x - getViewport().getWorldWidth() / 2 + 60);
        y -= (camera.position.y - getViewport().getWorldHeight() / 2 + 96);
        x /= zoom;
        y /= zoom;
        return new Point(x, y);
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
        camera.zoom = zoom;
    }

    public void addEntity(Entity e) {
        addActor(e);
        e.setSprite();
        if (e instanceof Shell)
            shells.add((Shell) e);
        if (e instanceof Tower)
            towers.add((Tower) e);
        if (e instanceof Enemy)
            enemies.add((Enemy) e);
        entities.add(e);
    }

    public void remove(Entity e) {
        if (e instanceof Shell)
            shells.removeValue((Shell) e, true);
        if (e instanceof Tower)
            towers.removeValue((Tower) e, true);
        if (e instanceof Enemy)
            enemies.removeValue((Enemy) e, true);
        entities.removeValue(e, true);
        e.remove();
    }

    public void addEnemy(Enemy en, int way) {
        if (way >= level.way.size) way = 0;
        float x = level.way.get(way).get(0).x * 64;
        float y = level.way.get(way).get(0).y * 64;
        en.setPosition(x, y);
        en.setWay(way);
        addEntity(en);
    }

    public Tower getTower(int x, int y) {
        for (Tower t : towers) {
            int tx = (int) (t.getX() / 64);
            int ty = (int) (t.getY() / 64);
            if (tx == x & ty == y) {
                return t;
            }
        }
        return null;
    }

    public Game(Viewport vp) {
        super(vp);
        loadAssets();
        initCamera(vp);
        initViewport(vp);
    }

    public void start() {
        initMap();
        initListener();
        initGUI();
    }

    public void initGUI() {
        tmp.gui = new Container() {
            @Override
            public void touchUp(Touch touch) {
                if (!drag) {
                    Point p = toWorldCoors(touch.getX(), touch.getY());
                    float x = p.getX();
                    float y = p.getY();
                    x = (int) (x / 64);
                    y = (int) (y / 32);
                    Tile tile = tiles[(int) y][(int) x];
                    if (!tile.free) {
                        Tower t = getTower((int) x, (int) y);
                        t.tapped = !t.tapped;
                        p = toScreenCoors(x * 64 + 32, (y + 1) * 32);
                        setShop(t, p.getX(), p.getY());
                    } else if (tile.buildable) {
                        if (gold >= BasicTower.fixedprice) {
                            BasicTower t = new BasicTower(x, y);
                            addEntity(t);
                            gold -= BasicTower.fixedprice;
                            tile.free = false;
                        }
                    }
                } else {
                    drag = false;
                }
            }

            @Override
            public void touchDragged(Touch touch) {
                drag = true;
                camera.translate(-touch.getDx() / 1.5f, -touch.getDy() / 1.5f);
            }

            @Override
            public void touchDown(Touch touch) {
                for (Tower ta : towers) {
                    ta.tapped = false;
                }

                while (shop.size > 0) {
                    tmp.gui.remove(shop.pop());
                }
            }

        };
        Container gui = tmp.gui;
        gui.show();
        gui.setX(getViewport().getWorldWidth() / 2);
        gui.setY(getViewport().getWorldHeight() / 2);
        gui.setWidth(getWidth());
        gui.setHeight(getHeight());
        gui.add(new Button("upgrade", getWidth() - 64, getHeight() - 64, 64, 64) {
            @Override
            public void touchDown(Touch touch) {
                MyGdxGame.MYGDXGAME.setCurrent(MyGdxGame.MYGDXGAME.menu);
            }

            @Override
            public void touchDragged(Touch touch) {

            }

            @Override
            public void touchUp(Touch touch) {

            }

            @Override
            public void touchHover(Touch touch) {

            }
        });
    }

    public void setShop(Tower target, float x, float y) {
        if (target instanceof BasicTower) {
            ShopButton sa = new ShopButton("upgrade_type", x, y, 40, 40) {
                @Override
                public void setTower() {
                    upgrade = new SplashTower(0, 0);
                }
            };
            shop.add(sa);
            tmp.gui.add(sa);

        }
        ShopButton sb = new ShopButton("destroy", x, y, 40, 40);
        shop.add(sb);
        tmp.gui.add(sb);
        float angle = 360 / shop.size;
        for (int i = 0; i < shop.size; i++) {
            ShopButton b = shop.get(i);
            b.angle = angle * (i / 2 + 1) * (float) Math.pow(-1, i);
            b.r = 60;
            b.target = target;
            b.show();
        }
    }

    public void loadAssets() {
        loader.load("textures.pack", TextureAtlas.class);
        loader.finishLoading();
        atlas = loader.get("textures.pack", TextureAtlas.class);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    public void initCamera(Viewport vp) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, vp.getWorldWidth(), vp.getWorldHeight());
        camera.zoom = zoom;
        camera.update();
        guiCamera = new OrthographicCamera();
        guiCamera.setToOrtho(false, vp.getWorldWidth(), vp.getWorldHeight());
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);
    }

    public void initMap() {
        level = new Level(1);
        level.generateMap(game);
        gold = level.startgold;
        wave = new Timer(20, true) {
            @Override
            public void act(float delta) {
                if (!spawn.isRunning()) {
                    super.act(delta);
                }
            }

            @Override
            public void action() {
                currentwave++;
                if (currentwave - 1 < level.waves) {
                    int count = level.wave.get(currentwave - 1).size;
                    spawn = new SpawnTimer(level.wave.get(currentwave - 1), 0.5f);
                    spawn.start();
                } else {
                    System.exit(0);
                }
            }
        };
        int count = level.wave.get(currentwave).size;
        spawn = new SpawnTimer(level.wave.get(currentwave), 0.5f);
        wave.start();
    }

    public void initViewport(Viewport vp) {
        vp.setCamera(guiCamera);
        setViewport(vp);
    }

    public void initListener() {
        tmp = new TouchHandlerExample() {
            @Override
            public boolean touchDownWorld(float x, float y) {
                return true;
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.LEFT) {
                    camera.translate(-16, 0);
                    if (camera.position.x < getWidth() / 2 - 60) {
                        camera.position.x = getWidth() / 2 - 60;
                    }
                }
                if (keycode == Input.Keys.RIGHT) {
                    camera.translate(16, 0);
                    if (camera.position.x > getWidth() * 2 + 60) {
                        camera.position.x = getWidth() * 2 + 60;
                    }
                }
                if (keycode == Input.Keys.UP) {
                    camera.translate(0, 16);
                    if (camera.position.y > getHeight()) {
                        camera.position.y = getHeight();
                    }
                }
                if (keycode == Input.Keys.DOWN) {
                    camera.translate(0, -16);
                    if (camera.position.y < getHeight() / 2 - 96) {
                        camera.position.y = getHeight() / 2 - 96;
                    }
                }
                return false;
            }
        };
        tmp.create();
        addListener(tmp);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        tmp.gui.act(delta);
        super.act(delta);
        spawn.act(delta);
        wave.act(delta);
        handleCamera();
        if (level.lives <= 0) MyGdxGame.MYGDXGAME.setCurrent(MyGdxGame.MYGDXGAME.menu);
    }

    public void handleCamera() {
        if (camera.position.x < getWidth() / 2 - 60) {
            camera.position.x = getWidth() / 2 - 60;
        }
        if (camera.position.x > getWidth() * 2 + 60) {
            camera.position.x = getWidth() * 2 + 60;
        }
        if (camera.position.y > getHeight()) {
            camera.position.y = getHeight();
        }
        if (camera.position.y < getHeight() / 2 - 96) {
            camera.position.y = getHeight() / 2 - 96;
        }
    }

    @Override
    public void draw() {
        Batch b = getBatch();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        guiCamera.update();
        b.setProjectionMatrix(camera.combined);
        drawTerrain(b);
        drawEntities(b);
        b.setProjectionMatrix(guiCamera.combined);
        drawGUI(b);
        b.end();
    }

    public void drawTerrain(Batch b) {
        b.begin();
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x].draw(b, x * 64, y * 32);
            }
        }
        b.end();
    }

    public void drawGUI(Batch b) {
        b.begin();
        font.setColor(Color.RED);
        font.draw(b, level.lives + "", 20, getHeight() - 40);
        font.setColor(Color.GREEN);
        font.draw(b, (int) wave.current + "", getWidth() / 2 - 40, getHeight() - 40);
        font.setColor(Color.WHITE);
        font.draw(b, (int) currentwave + "", getWidth() - 100, getHeight() - 40);
        font.setColor(Color.YELLOW);
        font.draw(b, (int) gold + "", getWidth() / 2 - 40, getHeight() - 80);
        tmp.gui.render(b, Gdx.graphics.getDeltaTime());
    }

    public void drawEntities(Batch b) {
        entities.sort();
        b.begin();
        for (Entity e : entities) {
            e.draw(b, 1);
        }
        b.end();
    }

}