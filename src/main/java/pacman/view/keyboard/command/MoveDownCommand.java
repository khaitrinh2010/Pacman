package pacman.view.keyboard.command;

import pacman.model.entity.dynamic.player.Controllable;

public class MoveDownCommand implements Command {
    private Controllable pacman;
    public MoveDownCommand(Controllable pacman) {
        this.pacman = pacman;
    }
    @Override
    public void execute() {
        pacman.down();
    }
}
