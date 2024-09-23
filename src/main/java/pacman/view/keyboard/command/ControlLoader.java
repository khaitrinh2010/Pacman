package pacman.view.keyboard.command;

import javafx.scene.input.KeyCode;
import pacman.model.entity.dynamic.player.Controllable;

public class ControlLoader {
    private RemoteController remoteController;
    public ControlLoader(Controllable pacman) {
        init(pacman);
    }
    public void init(Controllable pacman){
        remoteController = new RemoteController(pacman);
        remoteController.setCommand(KeyCode.LEFT, new MoveLeftCommand(pacman));
        remoteController.setCommand(KeyCode.DOWN, new MoveDownCommand(pacman));
        remoteController.setCommand(KeyCode.RIGHT, new MoveRightCommand(pacman));
        remoteController.setCommand(KeyCode.UP, new MoveUpCommand(pacman));

    }
    public RemoteController getRemoteController(){
        return remoteController;
    }
}
