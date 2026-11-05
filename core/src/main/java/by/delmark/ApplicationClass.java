package by.delmark;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ApplicationClass extends ApplicationAdapter {

    private Stage mainScreen;
    private Container<Actor> topBarContainer;
    private Container<Actor> applicationContainer;

    private Texture pixelTexture;

    @Override
    public void create() {
        mainScreen = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(mainScreen);
        loadTextures();
        setUpContainers();
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
        Pixmap pixel = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixel.setColor(Color.WHITE);
        pixel.fill();
        pixelTexture = new Texture(pixel);
        pixel.dispose();
    }

    private void setUpContainers() {
        TextureRegionDrawable drawable = new TextureRegionDrawable(pixelTexture);

        topBarContainer = new Container<>();
        topBarContainer.align(Align.center);
        topBarContainer.setBackground(drawable);

        applicationContainer = new Container<>();
        applicationContainer.setBackground(drawable.tint(Color.RED));

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
