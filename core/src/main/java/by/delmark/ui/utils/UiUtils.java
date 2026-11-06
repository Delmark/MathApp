package by.delmark.ui.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UiUtils {

    public static void addClickListener(Button button, Runnable action) {
        button.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                if (button.isDisabled()) return;
                action.run();
            }
        });
    }
}
