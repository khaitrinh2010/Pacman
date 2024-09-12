package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.*;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanVisual;
import pacman.util.ImageLoader;
import java.util.HashMap;
import java.util.Map;

public class PacmanFactory extends DynamicEntityFactory {
    public PacmanFactory(Image image){
        super(image);
    }
    @Override
    public Renderable createEntity(Vector2D topLeftPosition){
        BoundingBox pacmanBoundingBox = new BoundingBoxImpl(topLeftPosition, this.image.getHeight(), this.image.getWidth());
        KinematicState pacmanKinematicState = new KinematicStateImpl.KinematicStateBuilder().build();
        pacmanKinematicState.setPosition(topLeftPosition);
        return new Pacman(this.image, mapPacmanVisualToImage(), pacmanBoundingBox, pacmanKinematicState);
    }

    public Map<PacmanVisual, Image> mapPacmanVisualToImage(){
        Map<PacmanVisual, Image> result = new HashMap<>();
        result.putIfAbsent(PacmanVisual.UP, ImageLoader.INSTANCE().getImagesMapper().get("pacmanUp"));
        result.putIfAbsent(PacmanVisual.DOWN, ImageLoader.INSTANCE().getImagesMapper().get("pacmanDown"));
        result.putIfAbsent(PacmanVisual.LEFT, ImageLoader.INSTANCE().getImagesMapper().get("pacmanLeft"));
        result.putIfAbsent(PacmanVisual.RIGHT, ImageLoader.INSTANCE().getImagesMapper().get("pacmanRight"));
        result.putIfAbsent(PacmanVisual.CLOSED, ImageLoader.INSTANCE().getImagesMapper().get("pacmanClosed"));
        return result;
    }
}
