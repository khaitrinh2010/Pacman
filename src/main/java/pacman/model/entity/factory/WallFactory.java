package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.StaticEntityImpl;


public class WallFactory extends StaticEntityFactory {
    public WallFactory(Image image){
        super(image);
    }
    @Override
    public Renderable createEntity(Vector2D topLeftPosition) {
        BoundingBox boundingBox = new BoundingBoxImpl(topLeftPosition, this.image.getHeight(), this.image.getWidth());
        return new StaticEntityImpl(boundingBox, Renderable.Layer.BACKGROUND, this.image);
    }
}
