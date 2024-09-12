package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.StaticEntityImpl;

import java.io.FileNotFoundException;

public class CellFactory extends StaticEntityFactory{
    public CellFactory(Image image){
        super(image);
    }
    @Override
    public Renderable createEntity(Vector2D topLeftPosition){
        BoundingBox cellBoundingBox = new BoundingBoxImpl(topLeftPosition, 16, 16);
        return new StaticEntityImpl(cellBoundingBox, Renderable.Layer.BACKGROUND, null);
    }
}
