package by.delmark;

import by.delmark.ui.views.ApplicationMainWindow;
import by.delmark.ui.TextureManager;
import by.delmark.ui.views.ApplicationTopBar;
import by.delmark.ui.listeners.AppModeSwitchListener;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ApplicationClass extends ApplicationAdapter {

    private Stage mainScreen;
    private ApplicationTopBar topBar;
    private ApplicationMainWindow mainWindow;
    private final TextureManager textureManager = TextureManager.getInstance();

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
        topBar = new ApplicationTopBar();
        mainWindow = new ApplicationMainWindow();

        // регаем хэндлер событий на нажатия кнопочек по смене окон в топ баре
        topBar.addListener(new AppModeSwitchListener(mainWindow));

        mainScreen.addActor(topBar);
        mainScreen.addActor(mainWindow);

        updateContainerBounds();
    }

    private void updateContainerBounds() {
        float width = mainScreen.getWidth();
        float height = mainScreen.getHeight();

        float topBarHeight = Math.max(50, mainScreen.getHeight() * 0.06f);
        float appContainerHeight = height - topBarHeight;

        topBar.setBounds(0, appContainerHeight, width, topBarHeight);
        mainWindow.setBounds(0, 0, width, appContainerHeight);
    }
}
