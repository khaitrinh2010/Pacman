package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.util.ImageLoader;
import pacman.model.entity.staticentity.collectable.Pellet;

public class PelletFactory extends StaticEntityFactory {
    public PelletFactory(Image image){
        super(image);
    }
    @Override
    public Renderable createEntity(Vector2D topLeftPosition){
        BoundingBox pelletBoundingBox = new BoundingBoxImpl(topLeftPosition, this.image.getHeight(), this.image.getWidth());
        Renderable.Layer pelletLayer = Renderable.Layer.FOREGROUND;
        return new Pellet(pelletBoundingBox, pelletLayer, this.image, 1);
    }
}
