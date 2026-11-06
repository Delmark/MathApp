package by.delmark.ui.views;

import by.delmark.ui.TextureManager;
import by.delmark.ui.utils.CatppuchinPalette;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ApplicationMainWindow extends Container<Table> {

    private ApplicationMode appMode = ApplicationMode.CALC;

    private CalculatorWindow calculatorWindow;
    private GraphWindow graphWindow;

    public ApplicationMainWindow() {
        super();
        TextureManager textureManager = TextureManager.getInstance();
        this.setBackground(textureManager.getBaseDrawable().tint(CatppuchinPalette.BASE_BACKGROUND));
        fill();
        initWindows();
    }

    private void initWindows() {
        calculatorWindow = new CalculatorWindow();
        graphWindow = new GraphWindow();
        this.setActor(calculatorWindow);
    }

    public void changeScreenTo(ApplicationMode applicationMode) {
        if (appMode.equals(applicationMode)) {
            return;
        }
        appMode = applicationMode;
        switch (applicationMode) {
            case CALC -> this.setActor(calculatorWindow);
            case GRAPH -> this.setActor(graphWindow);
            case null, default -> throw new IllegalStateException();
        }
    }
}
