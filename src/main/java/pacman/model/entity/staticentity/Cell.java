package pacman.model.entity.staticentity;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.physics.BoundingBox;

public class Cell extends StaticEntityImpl{
    public Cell(BoundingBox boundingBox, Layer layer, Image image) {
        super(boundingBox, layer, image);
    }

    @Override
    public boolean canPassThrough(){return true;}
}
