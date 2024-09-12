package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

import java.io.FileNotFoundException;

public abstract class StaticEntityFactory extends EntityFactory{
    public StaticEntityFactory(Image image){
        super(image);
    }
    protected Renderable.Layer layer;
    @Override
    public abstract Renderable createEntity(Vector2D topLeftPosition);
}
