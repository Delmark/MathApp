package by.delmark.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TopBarContainer extends Table {

    private Button calculatorButton;
    private Button graphingButton;
    private TextureRegionDrawable drawable;

    public TopBarContainer(TextureRegionDrawable drawable) {
        super();
        this.drawable = drawable;
        this.setBackground(drawable.tint(new Color(0x737994FF)));
        initButtons();
    }

    private void initButtons() {
        Drawable buttonDrawable = drawable.tint(new Color(0x626880FF));
        calculatorButton = new Button(buttonDrawable);
        graphingButton = new Button(buttonDrawable);

        add(calculatorButton)
            .width(Value.percentWidth(0.05f, this))
            .height(Value.percentHeight(0.95f, this))
            .pad(5f);

        // уродливые хэндлеры, придумай чето получше
        calculatorButton.addListener(_ -> {
            if (!calculatorButton.getClickListener().isPressed()) {
                return false;
            }
            fire(new ModeSwitchEvent(ApplicationMode.CALC));
            return true;
        });

        graphingButton.addListener(_ -> {
            if (!graphingButton.getClickListener().isPressed()) {
                return false;
            }
            fire(new ModeSwitchEvent(ApplicationMode.GRAPH));
            return true;
        });

        add(graphingButton)
            .width(Value.percentWidth(0.05f,this))
            .height(Value.percentHeight(0.95f, this))
            .pad(5f);
    }

    public Button getCalculatorButton() {
        return calculatorButton;
    }

    public Button getGraphingButton() {
        return graphingButton;
    }
}
