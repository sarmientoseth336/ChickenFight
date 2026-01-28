package cf.main;

import cf.main.assets.AssetService;
import cf.main.screens.MapScreen;
import cf.main.screens.MenuScreen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    public static final float WORLD_WIDTH = 1280; // WIDTH
    public static final float WORLD_HEIGHT = 720; // HEIGHT

    private Batch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetService assetService;
    private GLProfiler glProfiler;
    private FPSLogger fpsLogger;

    private final Map<Class<? extends Screen>, Screen> screenCache = new HashMap<>();

    /// CREATE METHOD
    /// DO NOT RENDER HERE
    /// ONLY CREATE INSTANCES, RESOURCES, AND ELEMENTS
    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.assetService = new AssetService(new InternalFileHandleResolver());
        this.glProfiler = new GLProfiler(Gdx.graphics);
        this.fpsLogger = new FPSLogger();

        addScreen(new MenuScreen(this));
        setScreen(MenuScreen.class);
    }

    /// MAIN DISPLAY LOOP
    @Override
    public void render() {
        glProfiler.reset();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();

        fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        super.resize(width, height);
    }

    ///  DISPOSAL METHOD
    ///  PREVENT MEMORY LEAK
    ///  !!! VERY IMPORTANT TO CLOSE !!!
    @Override
    public void dispose() {
        screenCache.values().forEach(Screen::dispose);
        screenCache.clear();

        this.batch.dispose();
        this.assetService.debugDiagnostics();
        this.assetService.dispose();
    }

    /// HELPER METHODS
    public void addScreen(Screen screen) {
        screenCache.put(screen.getClass(), screen);
    }

    public void setScreen(Class<? extends Screen> screenClass) {
        Screen screen = screenCache.get(screenClass);
        if (screen == null) {
            throw new RuntimeException("Screen not found");
        }
        super.setScreen(screen);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Batch getBatch() {
        return batch;
    }

    public AssetService getAssetService() {
        return assetService;
    }
}
