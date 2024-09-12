package pacman.model.entity.factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

public interface EntityFactoryRegistry {
    EntityFactory getFactoryBasedOnCharacter(char character);
}
