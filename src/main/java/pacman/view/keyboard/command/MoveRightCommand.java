package pacman.view.keyboard.command;

import pacman.model.entity.dynamic.player.Controllable;

public class MoveRightCommand implements Command {
    private Controllable pacman;
    public MoveRightCommand(Controllable pacman) {
        this.pacman = pacman;
    }
    @Override
    public void execute() {
        pacman.right();
    }
}
