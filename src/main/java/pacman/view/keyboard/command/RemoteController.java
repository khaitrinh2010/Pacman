package pacman.view.keyboard.command;

import javafx.scene.input.KeyCode;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;
import pacman.model.entity.dynamic.player.Pacman;

import java.util.HashMap;
import java.util.Map;

public class RemoteController {
    Map<KeyCode, Command> commands = new HashMap<>();
    Map<Direction, KeyCode> keyCodes = new HashMap<>();
    private Controllable pacman;
    public RemoteController(Controllable pacman){
        for(KeyCode keyCode : KeyCode.values()){
            commands.put(keyCode, new NoCommand());
        }
        keyCodes.put(Direction.UP, KeyCode.UP);
        keyCodes.put(Direction.DOWN, KeyCode.DOWN);
        keyCodes.put(Direction.LEFT, KeyCode.LEFT);
        keyCodes.put(Direction.RIGHT, KeyCode.RIGHT);
        this.pacman = pacman;
    }
    public void setCommand(KeyCode code, Command command){
        commands.put(code, command);
    }
    public void onKeyPressed(KeyCode code){
        commands.get(code).execute();
    }
}
