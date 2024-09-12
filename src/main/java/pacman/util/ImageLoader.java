package pacman.util;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageLoader {
    static Map<String, Image> imagesMapper = new HashMap<>();
    private static volatile ImageLoader instance = new ImageLoader();

    private ImageLoader(){
        init();
    }
    public static ImageLoader INSTANCE(){
        if(instance != null){
            return instance;
        }
        return new ImageLoader();
    }
    public Map<String, Image> getImagesMapper(){
        return imagesMapper;
    }
    public void init(){
        imagesMapper.putIfAbsent("pacmanClosed", createImageObjectBasedOnFilename(ImageFile.PACMAN_CLOSED));
        imagesMapper.putIfAbsent("pacmanDown", createImageObjectBasedOnFilename(ImageFile.PACMAN_DOWN));
        imagesMapper.putIfAbsent("pacmanLeft", createImageObjectBasedOnFilename(ImageFile.PACMAN_LEFT));
        imagesMapper.putIfAbsent("pacmanRight", createImageObjectBasedOnFilename(ImageFile.PACMAN_RIGHT));
        imagesMapper.putIfAbsent("pacmanUp", createImageObjectBasedOnFilename(ImageFile.PACMAN_UP));
        imagesMapper.putIfAbsent("wallDownLeft", createImageObjectBasedOnFilename(ImageFile.WALL_DOWN_LEFT));
        imagesMapper.putIfAbsent("wallDownRight", createImageObjectBasedOnFilename(ImageFile.WALL_DOWN_RIGHT));
        imagesMapper.putIfAbsent("wallHorizontal", createImageObjectBasedOnFilename(ImageFile.WALL_HORIZONTAL));
        imagesMapper.putIfAbsent("wallUpLeft", createImageObjectBasedOnFilename(ImageFile.WALL_UP_LEFT));
        imagesMapper.putIfAbsent("wallUpRight", createImageObjectBasedOnFilename(ImageFile.WALL_UP_RIGHT));
        imagesMapper.putIfAbsent("wallVertical", createImageObjectBasedOnFilename(ImageFile.WALL_VERTICAL));
        imagesMapper.putIfAbsent("pellet", createImageObjectBasedOnFilename(ImageFile.PELLET));
        imagesMapper.putIfAbsent("ghost", createImageObjectBasedOnFilename(ImageFile.GHOST));
    }
    public Image createImageObjectBasedOnFilename(String file){
        try{
            return new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(file)).toExternalForm()));
        }
        catch(Exception e){
            System.out.println("File not found, please make sure you enter the correct file name");
        }
        return null;
    }
}
