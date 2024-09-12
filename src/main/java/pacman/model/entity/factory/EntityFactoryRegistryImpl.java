package pacman.model.entity.factory;

import pacman.util.ImageLoader;

import java.util.HashMap;
import java.util.Map;

public class EntityFactoryRegistryImpl implements EntityFactoryRegistry {

    private final Map<Character, EntityFactory> factoryRegistry = new HashMap<>();

    public EntityFactoryRegistryImpl(){
        init();
    }

    public void init(){
        ImageLoader imageLoader = ImageLoader.INSTANCE();
        factoryRegistry.putIfAbsent('0', new CellFactory(null));
        factoryRegistry.put('p', new PacmanFactory(imageLoader.getImagesMapper().get("pacmanClosed")));
        factoryRegistry.put('g', new GhostFactory(imageLoader.getImagesMapper().get("ghost")));
        factoryRegistry.put('7', new PelletFactory(imageLoader.getImagesMapper().get("pellet")));
        factoryRegistry.put('1', new WallFactory(imageLoader.getImagesMapper().get("wallHorizontal")));
        factoryRegistry.put('2', new WallFactory(imageLoader.getImagesMapper().get("wallVertical")));
        factoryRegistry.put('3', new WallFactory(imageLoader.getImagesMapper().get("wallUpLeft")));
        factoryRegistry.put('4', new WallFactory(imageLoader.getImagesMapper().get("wallUpRight")));
        factoryRegistry.put('5', new WallFactory(imageLoader.getImagesMapper().get("wallDownLeft")));
        factoryRegistry.put('6', new WallFactory(imageLoader.getImagesMapper().get("wallDownRight")));
    }
    @Override
    public EntityFactory getFactoryBasedOnCharacter(char character) {
        return factoryRegistry.get(character);
    }
}
