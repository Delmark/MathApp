package by.delmark.ui;

import com.badlogic.gdx.scenes.scene2d.Event;

public class ModeSwitchEvent extends Event {
    private final ApplicationMode mode;

    public ModeSwitchEvent(ApplicationMode mode) {
        this.mode = mode;
    }

    public ApplicationMode getMode() {
        return mode;
    }
}
