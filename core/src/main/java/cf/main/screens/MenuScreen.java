package cf.main.screens;

import cf.main.MainGame;
import cf.main.assets.AssetService;
import cf.main.assets.BackgroundAssets;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen extends ScreenAdapter {
    final MainGame mainGame;
    private final Batch batch;
    private final Viewport viewport;
    private final OrthographicCamera camera;
    private final AssetService assetService;

    private Stage stage;
    private Texture background;

    private float delayTimer = 3f;
    private Boolean clicksAvailable = false;

    public MenuScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        this.batch = mainGame.getBatch();
        this.viewport= mainGame.getViewport();
        this.camera = mainGame.getCamera();
        this.assetService = mainGame.getAssetService();
    }

    @Override
    public void show() {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        background = this.assetService.load(BackgroundAssets.BACKGROUND_MAIN_MENU);

        Image backgroundImage = new Image(background);
        backgroundImage.setSize(
            viewport.getWorldWidth(),
            viewport.getWorldHeight()
        );

        backgroundImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!clicksAvailable) {
                    return;
                }
                mainGame.setScreen(new MapScreen(mainGame));
            }
        });


        stage.addActor(backgroundImage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (!clicksAvailable) {
            delayTimer -= delta;
            if (delayTimer <= 0) {
                clicksAvailable = true;
            }
        }

        this.viewport.apply();
        this.camera.update();

        batch.setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    };
}
