package pacman.model.entity.factory;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Ghost Factory
 */

public class GhostFactory extends DynamicEntityFactory {
    private final List<Vector2D> possibleTargetCorners = Arrays.asList(new Vector2D(16, 64), new Vector2D(26 * 16, 64), new Vector2D(16, 32 * 16), new Vector2D(26 * 16,32 * 16));
    public GhostFactory(Image image){
        super(image);
    }
    @Override
    public Renderable createEntity(Vector2D topLeftPosition){
        Vector2D startingPosition = new Vector2D(topLeftPosition.getX() + 4, topLeftPosition.getY() - 4);
        GhostMode initialGhostMode = GhostMode.SCATTER;
        BoundingBox ghostBoundingBox = new BoundingBoxImpl(topLeftPosition, this.image.getHeight(), this.image.getWidth());
        KinematicState ghostKinematicState = new KinematicStateImpl.KinematicStateBuilder().build();
        ghostKinematicState.setPosition(startingPosition);
        Vector2D ghostTargetCorner = getRandomTargetCorner();
        Direction initialDirection = getRandomDirection();
        return new GhostImpl(this.image, ghostBoundingBox, ghostKinematicState, initialGhostMode, ghostTargetCorner, initialDirection);
    }

    public Vector2D getRandomTargetCorner(){
        return possibleTargetCorners.get(new Random().nextInt(4));
    }

    public Direction getRandomDirection(){
        return Direction.values()[new Random().nextInt(4)];
    }
}
