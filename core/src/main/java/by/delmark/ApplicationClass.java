package by.delmark;

import by.delmark.ui.MainContainer;
import by.delmark.ui.TextureManager;
import by.delmark.ui.TopBarContainer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ApplicationClass extends ApplicationAdapter {

    private Stage mainScreen;
    private TopBarContainer topBarContainer;
    private MainContainer mainContainer;

    private TextureManager textureManager = new TextureManager();

    @Override
    public void create() {
        mainScreen = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(mainScreen);
        textureManager.loadTextures();
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
        textureManager.clearTextures();
    }

    private void setUpUI() {
        topBarContainer = new TopBarContainer(textureManager);
        mainContainer = new MainContainer(textureManager);
        topBarContainer.addListener(mainContainer.getSwitchModeListener());

        mainScreen.addActor(topBarContainer);
        mainScreen.addActor(mainContainer);

        updateContainerBounds();
    }

    private void updateContainerBounds() {
        float width = mainScreen.getWidth();
        float height = mainScreen.getHeight();

        float topBarHeight = Math.max(30, mainScreen.getHeight() * 0.05f);
        float appContainerHeight = height - topBarHeight;

        topBarContainer.setBounds(0, appContainerHeight, width, topBarHeight);
        mainContainer.setBounds(0, 0, width, appContainerHeight);
    }
}
