package cf.main.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public enum BackgroundAssets implements Asset<Texture> {
    BACKGROUND_MAP("MapBackground.png"),
    BACKGROUND_MAIN_MENU("MainMenuBackdrop.png");

    private final AssetDescriptor<Texture> descriptor;

    BackgroundAssets(String path) {
        descriptor = new AssetDescriptor<>(path, Texture.class);
    }

    @Override
    public AssetDescriptor<Texture> getDescriptor() {
        return descriptor;
    }
}
