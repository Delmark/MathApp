package by.delmark.ui.events;

import by.delmark.ui.views.ApplicationMode;
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
