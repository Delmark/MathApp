package by.delmark.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainContainer extends Table {

    private ApplicationMode appMode = ApplicationMode.CALC;

    private final EventListener switchModeListener = (event -> {
        if (event instanceof ModeSwitchEvent modeEvent) {
            switchModeTo(modeEvent.getMode());
            return true;
        }
        return false;
    });

    public MainContainer(TextureManager textureManager) {
        super();
        this.setBackground(textureManager.getBaseDrawable().tint(new Color(0x303446FF)));
    }

    public EventListener getSwitchModeListener() {
        return switchModeListener;
    }

    private void switchModeTo(ApplicationMode applicationMode) {
        if (appMode.equals(applicationMode)) {
            return;
        }
        appMode = applicationMode;
        switch (applicationMode) {
            case CALC -> renderCalculator();
            case GRAPH -> renderGraph();
            case null, default -> throw new IllegalStateException();
        }
    }

    public void renderCalculator() {
        System.out.println("calc check");
    }

    public void renderGraph() {
        System.out.println("graph check");
    }
}
