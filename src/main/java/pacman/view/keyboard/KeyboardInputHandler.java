package pacman.view.keyboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pacman.model.entity.dynamic.player.Controllable;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.view.keyboard.command.ControlLoader;
import pacman.view.keyboard.command.RemoteController;

/**
 * Responsible for handling keyboard input from player
 */
public class KeyboardInputHandler {
    private RemoteController remoteController;

    public KeyboardInputHandler() {
    }

    public void createController(Controllable pacman) {
        remoteController = new ControlLoader(pacman).getRemoteController();
    }

    public void handlePressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case LEFT:
                remoteController.onKeyPressed(KeyCode.LEFT);
                break;
            case RIGHT:
                remoteController.onKeyPressed(KeyCode.RIGHT);
                break;
            case DOWN:
                remoteController.onKeyPressed(KeyCode.DOWN);
                break;
            case UP:
                remoteController.onKeyPressed(KeyCode.UP);
                break;
        };
    }
}
