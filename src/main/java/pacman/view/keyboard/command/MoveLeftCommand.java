package pacman.view.keyboard.command;

import pacman.model.entity.dynamic.player.Controllable;

public class MoveLeftCommand implements Command {
    private Controllable pacman;
    public MoveLeftCommand(Controllable pacman) {
        this.pacman = pacman;
    }
    @Override
    public void execute() {
        pacman.left();
    }
}
