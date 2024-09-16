package pacman.view.keyboard.command;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class RemoteController {
    Map<KeyCode, Command> commands = new HashMap<>();
    public RemoteController(){
        for(KeyCode keyCode : KeyCode.values()){
            commands.put(keyCode, new NoCommand());
        }
    }
    public void setCommand(KeyCode code, Command command){
        commands.put(code, command);
    }
    public void onKeyPressed(KeyCode code){
        commands.get(code).execute();
    }
}
