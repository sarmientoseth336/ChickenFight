package cf.main.screens;

import cf.main.MainGame;
import cf.main.assets.AssetService;
import cf.main.assets.BackgroundAssets;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapScreen extends ScreenAdapter {
    final MainGame mainGame;
    private final Batch batch;
    private final Viewport viewport;
    private final OrthographicCamera camera;
    private final AssetService assetService;

    private Texture background;

    public MapScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        this.batch = mainGame.getBatch();
        this.viewport= mainGame.getViewport();
        this.camera = mainGame.getCamera();
        this.assetService = mainGame.getAssetService();
    }

    @Override
    public void show() {
        background = this.assetService.load(BackgroundAssets.BACKGROUND_MAP);
    }

    @Override
    public void render(float delta) {
        this.viewport.apply();
        this.camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 1280, 720);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    };
}

