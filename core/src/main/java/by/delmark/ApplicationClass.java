package by.delmark;

import by.delmark.ui.ApplicationContainer;
import by.delmark.ui.TopBarContainer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ApplicationClass extends ApplicationAdapter {

    private Stage mainScreen;
    private TopBarContainer topBarContainer;
    private ApplicationContainer applicationContainer;

    private Texture pixelTexture;
    TextureRegionDrawable drawable;

    @Override
    public void create() {
        mainScreen = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(mainScreen);
        loadTextures();
        setUpUI();
    }

    @Override
    public void resize(int width, int height) {
        mainScreen.getViewport().update(width, height, true);
        updateContainerBounds();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainScreen.act(delta);
        mainScreen.draw();
    }

    @Override
    public void dispose() {
        mainScreen.dispose();
        pixelTexture.dispose();
    }

    private void loadTextures() {
        Pixmap pixel = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixel.setColor(Color.WHITE);

        pixel.fill();
        pixelTexture = new Texture(pixel);
        pixel.dispose();

        drawable = new TextureRegionDrawable(pixelTexture);
    }

    private void setUpUI() {
        topBarContainer = new TopBarContainer(drawable);
        applicationContainer = new ApplicationContainer(drawable);
        topBarContainer.addListener(applicationContainer.getSwitchModeListener());

        mainScreen.addActor(topBarContainer);
        mainScreen.addActor(applicationContainer);

        updateContainerBounds();
    }

    private void updateContainerBounds() {
        float width = mainScreen.getWidth();
        float height = mainScreen.getHeight();

        float topBarHeight = Math.max(30, mainScreen.getHeight() * 0.05f);
        float appContainerHeight = height - topBarHeight;

        topBarContainer.setBounds(0, appContainerHeight, width, topBarHeight);
        applicationContainer.setBounds(0, 0, width, appContainerHeight);
    }
}
