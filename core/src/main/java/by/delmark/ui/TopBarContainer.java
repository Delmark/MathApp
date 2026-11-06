package by.delmark.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Null;

public class TopBarContainer extends Table {

    private final TextureManager textureManager;

    private Button calculatorButton;
    private Button graphingButton;

    private final Color upBtnColor = new Color(0x626880FF);
    private final Color downBtnColor = new Color(0x4B5063FF);

    public TopBarContainer(TextureManager textureManager) {
        super();
        this.textureManager = textureManager;

        Color topBarBG = new Color(0x737994FF);
        this.setBackground(textureManager.getBaseDrawable().tint(topBarBG));

        initButtons();
        left();
    }

    private void initButtons() {
        calculatorButton = initButton(ApplicationMode.CALC, textureManager.getCalcBtnIcon());
        graphingButton = initButton(ApplicationMode.GRAPH, textureManager.getGraphBtnIcon());
    }

    private Button initButton(ApplicationMode buttonMode, @Null Texture buttonIcon) {
        Drawable upDraw = textureManager.getBaseDrawable().tint(upBtnColor);
        Drawable downDraw = textureManager.getBaseDrawable().tint(downBtnColor);
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle(upDraw, downDraw, null);

        Button topBarButton = new Button(buttonStyle);
        topBarButton.align(Align.center);
        if (buttonIcon != null) {
            topBarButton.add(new Image(buttonIcon));
        }

        add(topBarButton)
            .width(25)
            .height(Value.percentHeight(0.80f, this))
            .pad(3f);

        // уродливые хэндлеры, придумай чето получше
        topBarButton.addListener(_ -> {
            if (!topBarButton.getClickListener().isPressed()) {
                return false;
            }
            fire(new ModeSwitchEvent(buttonMode));
            return true;
        });

        return topBarButton;
    }
}
