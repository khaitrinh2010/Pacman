package pacman.view.keyboard.command;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;

public class MoveLeftCommand implements Command {
    private Controllable pacman;
    public MoveLeftCommand(Controllable pacman) {
        this.pacman = pacman;
    }
    @Override
    public void execute() {
        if(pacman.isValidMove(Direction.LEFT)) {
            pacman.left();
        }
        else {
            pacman.setLastDirection(Direction.LEFT);
        }
    }
}
