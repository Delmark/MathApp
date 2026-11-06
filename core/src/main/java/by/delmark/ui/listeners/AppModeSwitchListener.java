package by.delmark.ui.listeners;

import by.delmark.ui.views.ApplicationMainWindow;
import by.delmark.ui.events.ModeSwitchEvent;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class AppModeSwitchListener implements EventListener {

    private final ApplicationMainWindow mainWindow;

    public AppModeSwitchListener(ApplicationMainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public boolean handle(Event event) {
        if (event instanceof ModeSwitchEvent modeEvent) {
            mainWindow.changeScreenTo(modeEvent.getMode());
            return true;
        }
        return false;
    }

}
