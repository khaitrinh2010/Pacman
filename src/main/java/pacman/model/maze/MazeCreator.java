package pacman.model.maze;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.factory.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Responsible for creating renderables and storing it in the Maze
 */
public class MazeCreator {

    private final String fileName;
    public static final int RESIZING_FACTOR = 16;
    private final EntityFactoryRegistry entityFactoryRegistry;;

    public MazeCreator(String fileName){
        this.fileName = fileName;
        this.entityFactoryRegistry = new EntityFactoryRegistryImpl();
    }

    public Maze createMaze(){
        File f = new File(this.fileName);
        Maze maze = new Maze();
        int count = 0;
        try {
            Scanner scanner = new Scanner(f);
            int y = 0;
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                char[] row = line.toCharArray();
                for (int x = 0; x < row.length; x++){
                    if(row[x] == '7'){
                        count += 1;
                    }
                    if(row[x] == '7' && count > 5){
                        continue;
                    }
                    EntityFactory entityFactory = entityFactoryRegistry.getFactoryBasedOnCharacter(row[x]);
                    Renderable entity = entityFactory.createEntity(new Vector2D(x * 16, y * 16));
                    maze.addRenderable(entity, row[x], x, y);
                }
                y += 1;
            }

            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("No maze file was found.");
            exit(0);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            exit(0);
        }

        return maze;
    }
}
