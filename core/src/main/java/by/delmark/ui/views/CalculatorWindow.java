package by.delmark.ui.views;

import by.delmark.ui.TextureManager;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CalculatorWindow extends Table {

    private final TextureManager textureManager = TextureManager.getInstance();
    private HorizontalGroup expressionView;
    private Table controlView;

    public CalculatorWindow() {
        super();
    }


}
