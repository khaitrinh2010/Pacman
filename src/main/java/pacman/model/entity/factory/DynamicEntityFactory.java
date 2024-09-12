package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.Vector2D;

import java.io.FileNotFoundException;

public abstract class DynamicEntityFactory extends EntityFactory{
    protected double speed;
    public DynamicEntityFactory(Image image){
        super(image);
    }
    @Override
    public abstract Renderable createEntity(Vector2D topLeftPosition);
}
