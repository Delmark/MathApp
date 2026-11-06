package by.delmark.ui.views;

import by.delmark.ui.TextureManager;
import by.delmark.ui.utils.UiUtils;
import by.delmark.ui.events.ModeSwitchEvent;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Null;

public class ApplicationTopBar extends Table {

    private final TextureManager textureManager = TextureManager.getInstance();
    private final Color upBtnColor = new Color(0x626880FF);
    private final Color downBtnColor = new Color(0x4B5063FF);

    public ApplicationTopBar() {
        super();
        Color topBarBG = new Color(0x737994FF);
        this.setBackground(textureManager.getBaseDrawable().tint(topBarBG));

        initButtons();
        left();
    }

    private void initButtons() {
        initButton(ApplicationMode.CALC, textureManager.getCalcBtnIcon());
        initButton(ApplicationMode.GRAPH, textureManager.getGraphBtnIcon());
    }

    private void initButton(ApplicationMode buttonMode, @Null Texture buttonIcon) {
        Drawable upDraw = textureManager.getBaseDrawable().tint(upBtnColor);
        Drawable downDraw = textureManager.getBaseDrawable().tint(downBtnColor);
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle(upDraw, downDraw, null);

        Button topBarButton = new Button(buttonStyle);
        topBarButton.align(Align.center);
        if (buttonIcon != null) {
            topBarButton.add(new Image(buttonIcon)).grow();
        }

        add(topBarButton)
            .size(Value.percentHeight(0.85f, this))
            .pad(3f);

        UiUtils.addClickListener(topBarButton, () -> topBarButton.fire(new ModeSwitchEvent(buttonMode)));
    }
}
