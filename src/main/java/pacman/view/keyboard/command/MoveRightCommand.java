package pacman.view.keyboard.command;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;

public class MoveRightCommand implements Command {
    private Controllable pacman;
    public MoveRightCommand(Controllable pacman) {
        this.pacman = pacman;
    }
    @Override
    public void execute() {
        if(pacman.isValidMove(Direction.RIGHT)) {
            pacman.right();
        }
        else {
            pacman.setLastDirection(Direction.RIGHT);
        }
    }
}
