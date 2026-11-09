package by.delmark.ui.views;

import by.delmark.ui.TextureManager;
import by.delmark.ui.utils.CatppuchinPalette;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class CalculatorWindow extends Table {

    private final TextureManager textureManager = TextureManager.getInstance();
    private Container<Actor> expressionPanel = new Container<>();
    private Table functionBar = new Table();
    private Table calcKeyboard = new Table();

    public CalculatorWindow() {
        super();
        initPanels();
    }

    private void initPanels() {
        expressionPanel.setBackground(textureManager.getBaseDrawable().tint(CatppuchinPalette.OVERLAY_1));
        calcKeyboard.setBackground(textureManager.getBaseDrawable().tint(CatppuchinPalette.MANTLE));

        Table calculatorLayout = new Table();

        Value pad = Value.percentHeight(0.02f, calculatorLayout);
        calculatorLayout.pad(pad);

        add(calculatorLayout)
            .width(Value.percentWidth(0.8f, this))
            .height(Value.percentHeight(1, this));
        calculatorLayout.add(expressionPanel)
            .height(Value.percentHeight(0.12f, calculatorLayout))
            .width(Value.percentWidth(0.95f, calculatorLayout))
            .padBottom(pad)
            .row();
        calculatorLayout.add(calcKeyboard).grow();
        initKeyboardButtons();

        functionBar.setBackground(textureManager.getBaseDrawable().tint(CatppuchinPalette.MANTLE));
        add(functionBar)
            .height(Value.percentHeight(1f, this))
            .grow();
    }

    private void initKeyboardButtons() {
        Value buttonPad = Value.percentHeight(0.045f, calcKeyboard);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(
            textureManager.getBaseDrawable().tint(CatppuchinPalette.SURFACE_2),
            textureManager.getBaseDrawable().tint(CatppuchinPalette.SURFACE_0),
            null,
            new BitmapFont()
        );

        String[][] keyLayout = {
            {"1", "2", "3", "+", "-"},
            {"4", "5", "6", "*", "/"},
            {"7", "8", "9", "(", ")"},
            {"C", "0", ".", "^", "="}
        };

        for (String[] row : keyLayout) {
            for (String symbol : row) {
                TextButton btn = new TextButton(symbol, buttonStyle);
                calcKeyboard.add(btn).pad(buttonPad).grow();
            }
            calcKeyboard.row();
        }
    }
}
